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
package avalam_s6.Core.File_IO;

import avalam_s6.Exceptions.GridSizeException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Convert a File (Levels) into a String.
 * This String is a Grid that will be converted by Grid Class
 * @author Team 7
 */
public class Level_Parser {

    String grid; // Grid
    String path; // File name

    /**
     * Constructor
     * @param s Name of the Level that the parser musts read.
     */
    public Level_Parser(String s) {
        this.path = "./ressources/Levels/" + s;
    }

    /**
     * Read the grid written in the file, throws exception if the grid is
     * incorrectly written.
     *
     * @return The grid contained in the file named "path"
     * @throws IOException File not found or error while loading it
     * @throws GridSizeException The grid contained by the file is not 9x9
     */
    public String readLevel() throws IOException, GridSizeException {
        if (this.grid == null) { // Grid does not exists --> Read it
            byte[] encoded = Files.readAllBytes(Paths.get(this.path));
            this.grid = new String(encoded, StandardCharsets.UTF_8);
            grid = grid.replaceAll("\\r|\\n", "");
        }
        if (this.grid.length() == 81) {
            return this.grid; // Return the grid if it is correctly written
        } else {
            throw new GridSizeException();
        }
    }
}
