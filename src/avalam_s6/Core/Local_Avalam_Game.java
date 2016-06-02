/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Core.File_IO.Level_Parser;
import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Globals.Input;
import avalam_s6.Core.Globals.LanguageManager;
import avalam_s6.Exceptions.GridCharException;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.Player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.Timer;
import avalam_s6.GUI.GuiManager_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.Player.AIPlayer;
import avalam_s6.Player.AIPlayerEasy;
import avalam_s6.Player.AIPlayerHardAB;
import avalam_s6.Player.ControlledPlayer;
import java.io.IOException;

/**
 * The game. Implements Game_INTERFACE. This class basically is the game engine.
 * @author Team 7
 */
public class Local_Avalam_Game implements Game_INTERFACE, ActionListener {
    /* - LAG Builder - */

    private static final int NB_PLAYERS = 2;
    private final GuiManager_INTERFACE gui;
    private Grid grid;
    private Player[] players;
    private Stack<Move> history;
    private Stack<Move> cancelled_moves;
    private int current_player;
    private int nbTurns;
    /* - Game Logic - */
    private boolean isTurnFinished;
    private Timer t;
    private boolean isGameFinished;
    private boolean isGamePaused;
    
    /* Repaint IA Activity */
    private Move lastIAMove;

    /**
     * Default Constructor. Creates a Player vs easy AI game on the 
     * default board.
     * @param pGui the graphical user interface the game is linked to.
     * @throws GridSizeException if the Grid doesn't match the 9*9 
     * size requirements.
     * @throws GridCharException if the grid is invalid because of a 
     * non-tolerated character.
     * @throws IOException if there is any problem while reading the grid.
     */
    public Local_Avalam_Game(GuiManager_INTERFACE pGui) throws GridSizeException, GridCharException, IOException {
        this(pGui, new Grid(new Level_Parser("default").readLevel(), "default"), new ControlledPlayer("Jon Doe", AvalamColor.WHITE, Owner.PLAYER_1), new AIPlayerEasy("Bot_Frank", AvalamColor.BLACK, Owner.PLAYER_2), new Stack<>(), new Stack<>(), 0, 0);
    }

    /**
     * Constructor.
     * @param pGui the gui the game is linked to.
     * @param pGrid the grid of the game.
     * @param pPlayer1 the first player.
     * @param pPlayer2 the second player.
     * @param pUndo the history of moves.
     * @param pRedo the history of canceled moves.
     * @param pCurrent the id of the current player (one or two).
     * @param pTurns the current turn's number.
     */
    public Local_Avalam_Game(GuiManager_INTERFACE pGui, Grid pGrid, Player pPlayer1, Player pPlayer2, Stack<Move> pUndo, Stack<Move> pRedo, int pCurrent, int pTurns) {
        this.gui = pGui;
        this.grid = pGrid;
        this.players = new Player[2];
        this.players[0] = pPlayer1;
        this.players[1] = pPlayer2;
        this.history = pUndo;
        this.cancelled_moves = pRedo;
        this.current_player = pCurrent;
        this.nbTurns = pTurns;

        this.initGame();
    }

    /**
     * init the game. Init all components that are not constructor dependent.
     */
    private void initGame() {
        this.t = new Timer(100, (ActionListener) this);
        this.isTurnFinished = false;
        this.isGameFinished = winCheck() > 0;
        this.isGamePaused = this.isGameFinished;
        this.lastIAMove = null;
        Input.resetClick();
        Input.setInputGame(this);
        ((Main_Frame)this.gui).resetHint();
    }
    
    /**
     * Update the game's title (tells game's state).
     */
    public void updateTitle() {
        if (!this.isGameFinished) {
            if (this.isGamePaused) {
                ((Main_Frame) this.gui).setGameTitle("Pause");
            } else {
                ((Main_Frame) this.gui).setGameTitle(this.getCurrentPlayer().getName() + " " + LanguageManager.getElement("Joue"));
            }
        } else {
            winningProcedure(winCheck());
        }
    }

    /**
     * This is the turn's logic. It gets a move from the player and updates the 
     * game's status. It is regularly called by the timer.
     */
    private void playATurn() {
        /* Gestion Fin d'un tour */
        if (this.isTurnFinished) {
            int w = winCheck();
            System.gc();
            switch (w) {
                case 1:
                case 2:
                case 3:
                    t.stop();
                    this.isGameFinished = true;
                    this.isGamePaused = true;
                    this.winningProcedure(w);
                    return;
                case 0:
                default:
                    this.isGameFinished = false;
                    this.isTurnFinished = false;
                    break;
            }
        }
        /* Gestion d'un tour de jeu */
        if (this.players[this.current_player].isAI()) {
            ((AIPlayer) this.players[this.current_player]).setGame(this);
        }
        if (!this.isPaused()) {
            Move m = this.players[this.current_player].play();

            if (m != null) {
                if (this.players[this.current_player].isAI()) { // IA
                    this.lastIAMove = m;
                    this.grid.moveCell(m.getC_src(), m.getC_dst());
                    this.history.add(m);
                    this.isTurnFinished = true;
                    this.changeNbTurns(1);
                    this.updateTitle();
                } else if (this.grid.canStack(this.grid.getCellAt(m.getC_src()), this.grid.getCellAt(m.getC_dst()))) {
                    // JOUEUR
                    // MOVE OK
                    this.grid.moveCell(m.getC_src(), m.getC_dst());
                    this.history.add(m);
                    this.isTurnFinished = true;
                    this.changeNbTurns(1);
                    this.updateTitle();
                    this.lastIAMove = null;
                    ((Main_Frame)this.gui).resetHint();
                }
                this.cancelled_moves.clear();
            }
        }
        this.gui.render();
    }

    /**
     * Tells if the game has been won.
     *
     * @return 1 or 2 if player 1 or 2 won, 3 in case of a null match, 0 if game
     * isn't finished.
     */
    public int winCheck() {
        Coordinate[] c = new Coordinate[9];
        for (int i = 0; i < 9; i++) {
            c[i] = new Coordinate();
        }
        int score_p1 = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                c[0].setX(x);
                c[0].setY(y);
                c[1].setX(x - 1);
                c[1].setY(y - 1);
                c[2].setX(x - 1);
                c[2].setY(y);
                c[3].setX(x - 1);
                c[3].setY(y + 1);
                c[4].setX(x);
                c[4].setY(y - 1);
                c[5].setX(x);
                c[5].setY(y + 1);
                c[6].setX(x + 1);
                c[6].setY(y - 1);
                c[7].setX(x + 1);
                c[7].setY(y);
                c[8].setX(x + 1);
                c[8].setY(y + 1);
                for (int i = 1; i < 9; i++) {
                    if (this.grid.getCellAt(c[0]).getState().getValue() != CellState.RESTRICTED.getValue() || this.grid.getCellAt(c[0]).getState().getValue() != CellState.EMPTY.getValue()) {
                        if (c[i].isValid() && this.grid.getCellAt(c[i]).getState().getValue() != CellState.RESTRICTED.getValue() && this.grid.getCellAt(c[i]).getState().getValue() != CellState.EMPTY.getValue()) {
                            if (this.grid.canStack(this.grid.getCellAt(c[0]), this.grid.getCellAt(c[i]))) {
                                return 0;
                            }
                        }
                    }
                }
                if (this.grid.getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                    score_p1++;
                } else if (this.grid.getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_2.getValue()) {
                    score_p1--;
                }
            }
        }
        if (score_p1 > 0) {
            return 1;
        } else if (score_p1 == 0) {
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * Takes care of what happens when someone wins.
     *
     * @param i the id of the winner (3 if null match).
     */
    private void winningProcedure(int i) {
        if (i < 3) {
            ((Main_Frame) this.gui).setVictory(this.players[i - 1].getName() + " " + LanguageManager.getElement("Fin"));
        } else {
            ((Main_Frame) this.gui).setVictory(LanguageManager.getElement("Egalité"));
        }
    }

    @Override
    public void addMoveToHistory(Move m) {
        this.history.add(m);
    }

    /**
     * Changes the current turn's number. Updates the current player.
     * @param n the quantity of turns to add (can be negative for undo).
     */
    public void changeNbTurns(int n) {
        this.nbTurns += n;
        this.current_player = this.nbTurns % NB_PLAYERS;
    }

    /**
     * Toggles the pause state of the game.
     */
    public void togglePause() {
        this.isGamePaused = !this.isGamePaused;
        this.updateTitle();
        if (this.isGamePaused) {
            this.t.stop();
        } else {
            Input.resetClick();
            this.t.start();
        }
    }

    /**
     * Tells if the game is paused.
     * @return true if the game is paused, false otherwise.
     */
    public boolean isPaused() {
        return this.isGamePaused;
    }
    
    /**
     * Tells if the game is finished.
     * @return true if the game is finished, false otherwise.
     */
    public boolean isFinished() {
        return this.isGameFinished;
    }
    
    /**
     * Gets a hint from an efficient AI.
     * @return a possible efficient move.
     */
    @SuppressWarnings("unchecked")
    public Move getHint() {
        Stack<Move> backup = (Stack<Move>)this.history.clone();
        Stack<Move> backup2 = (Stack<Move>)this.cancelled_moves.clone();
        if (!this.players[current_player].isAI()) { // Le joueur actuel est un vrai joueur
            AIPlayerHardAB ia = new AIPlayerHardAB("Help",AvalamColor.WHITE,this.players[current_player].getOwner());
            ia.setGame(this);
            Input.resetClick();
            Move m  = ia.play();
            this.history = backup;
            this.cancelled_moves = backup2;
            return m;
        }
        return null;
    }

    /**
     * Gets the last move played by the AI.
     * @return the last AI's move.
     */
    public Move getLastIaMove() {
        return this.lastIAMove;
    }

    /**
     * Current player's getter.
     * @return the current player's id.
     */
    public int getCurrent() {
        return this.current_player;
    }

    /**
     * Moves' history getter.
     * @return the moves' history.
     */
    public Stack<Move> getHistory() {
        return this.history;
    }

    /**
     * Canceled moves' history.
     * @return the canceled moves' history.
     */
    public Stack<Move> getCancelled_moves() {
        return this.cancelled_moves;
    }

    /**
     * Players' getter.
     * @return the players.
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * Turn's number getter.
     * @return the current turn's number.
     */
    public int getTurns() {
        return this.nbTurns;
    }

    /**
     * Score getter.
     * @param pnumber the id of the player whose score we are seeking for.
     * @return the score of the chose player.
     */
    public int getScore(int pnumber) {
        Coordinate[] c = new Coordinate[9];
        for (int i = 0; i < 9; i++) {
            c[i] = new Coordinate();
        }
        int score_p1 = 0;
        int score_p2 = 0;
        boolean test;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                test = true;
                c[0].setX(x);
                c[0].setY(y);
                c[1].setX(x - 1);
                c[1].setY(y - 1);
                c[2].setX(x - 1);
                c[2].setY(y);
                c[3].setX(x - 1);
                c[3].setY(y + 1);
                c[4].setX(x);
                c[4].setY(y - 1);
                c[5].setX(x);
                c[5].setY(y + 1);
                c[6].setX(x + 1);
                c[6].setY(y - 1);
                c[7].setX(x + 1);
                c[7].setY(y);
                c[8].setX(x + 1);
                c[8].setY(y + 1);
                for (int i = 1; i < 9; i++) {
                    if (this.grid.getCellAt(c[0]).getState().getValue() != CellState.RESTRICTED.getValue() || this.grid.getCellAt(c[0]).getState().getValue() != CellState.EMPTY.getValue()) {
                        if (c[i].isValid() && this.grid.getCellAt(c[i]).getState().getValue() != CellState.RESTRICTED.getValue() && this.grid.getCellAt(c[i]).getState().getValue() != CellState.EMPTY.getValue()) {
                            if (this.grid.canStack(this.grid.getCellAt(c[0]), this.grid.getCellAt(c[i]))) {
                                test = false;
                            }
                        }
                    }
                }
                if (test == true) {
                    if (this.grid.getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                        score_p1++;
                    } else if (this.grid.getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_2.getValue()) {
                        score_p2++;
                    }
                }
            }
        }
        if (pnumber == 1) {
            return score_p1;
        } else {
            return score_p2;
        }
    }

    @Override
    public void undo() {
        ((Main_Frame)this.gui).resetHint();
        Input.resetClick();
        if (!this.history.isEmpty()) {
            this.cancelled_moves.add(this.history.pop());
            this.grid.undoMove(this.cancelled_moves.lastElement());
        }
        this.isGameFinished = false;
        this.lastIAMove = null;
    }

    @Override
    public void redo() {
        ((Main_Frame)this.gui).resetHint();
        Input.resetClick();
        if (!this.cancelled_moves.isEmpty()) {
            this.history.add(this.cancelled_moves.pop());
            this.grid.moveCell(this.history.lastElement().getC_src(), this.history.lastElement().getC_dst());
        }
        if (winCheck()>0) {
            this.isGameFinished = true;
        }
    }

    @Override
    public Timer getTimer() {
        return this.t;
    }
    
    @Override
    public Grid getGrid() {
        return this.grid;
    }

    @Override
    public void setGrid(Grid g) {
        this.grid = g;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.players[this.current_player];
    }

    @Override
    public void clean() {
        this.players[0] = null;
        this.players[1] = null;
        this.history = null;
        this.cancelled_moves = null;
        this.grid = null;

        System.gc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.playATurn();
    }
}
