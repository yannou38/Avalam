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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Directory Parser, List the Files contained into ./ressources/Levels .
 * @author Team 7
 */
public class GridLister {
    /**
     * List the Levels Files
     * @return Table filled with Grid's names
     */
    public static String[] listGrids() {
        Path p = Paths.get("./ressources/Levels");
        if (Files.exists(p) && Files.isDirectory(p)) {
            File f = new File("./ressources/Levels");
            String[] rStr = new String[f.list().length -2]; // Remove README and tutolevel
            int x = 0;
            for (String s : f.list()) {
                if (!s.equals("Read_Me.txt") && !s.equals("tutolevel")) {
                    //System.out.println(s);
                    rStr[x] = s;
                    x++;
                }
            }
            return rStr;
        }
        return null;
    }
}