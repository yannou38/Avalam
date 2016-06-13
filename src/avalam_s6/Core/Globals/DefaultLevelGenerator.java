/* 
 * Copyright (C) 2016 Yann Ducruy <yann.ducruy@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package avalam_s6.Core.Globals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generate a default level file if it not exists.
 * The class do not override existing files.
 * If user encounters any problem with default level file he can delete it.
 * @author Team 7
 */
public class DefaultLevelGenerator {

    /**
     * Generate a default Level (./ressources/Levels/default)
     */
    public static void generate() {
        if (!Files.exists(Paths.get("./ressources/Levels/default"))) {
            try (PrintWriter writer = new PrintWriter("./ressources/Levels/default", "UTF-8")) {
                writer.print("RR1ARRRRR\nR1A1ARRRR\nRA1A1A1RR\nR1A1A1A1A\n1A1A0A1A1\nA1A1A1A1R\nRR1A1A1AR\nRRRRA1A1R\nRRRRRA1RR");
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                System.out.println("Error - " + DefaultLevelGenerator.class.toString());
                Logger.getLogger(DefaultLevelGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
