/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.File_IO;

import avalam_s6.Exceptions.GridSizeException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author sazeratj
 */
public class Level_Parser {
    String grid; // Grid
    String path; // File name
    
    public Level_Parser(String s) {
        this.path = "./ressources/Levels/"+s;
    }
    
    /**
     * Access the grid written in the file, throw exception if the grid is incorrectly written.
     * @return The grid contained in the file named "path"
     * @throws IOException File not found or error while loqding it
     * @throws GridSizeException The grid contained by the file is not 9x9
     */
    public String readLevel() throws IOException,GridSizeException  {
        if (this.grid==null) {
            byte[] encoded = Files.readAllBytes(Paths.get(this.path));
            this.grid = new String(encoded, StandardCharsets.UTF_8);
        }
        if (this.grid.length() == 81) {            
            return this.grid;
        } else
            throw new GridSizeException();
    }
}
