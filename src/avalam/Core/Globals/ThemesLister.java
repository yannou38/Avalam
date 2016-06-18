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
package avalam.Core.Globals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Directory Parser, List every themes.
 * @author Team 7
 */
public class ThemesLister {

    /**
     * List Themes (./ressources/Themes/...) depending of current language.
     * @param language : the current language in the options screen
     * @return List with every theme name.
     */
    public static String[] listThemes(String language) {
        Path p = Paths.get("./ressources/Themes/"+language);
        if (Files.exists(p) && Files.isDirectory(p)) {
            File f = new File("./ressources/Themes/"+language);
            int x = f.list().length;
            for (String s : f.list()) {
                if (s.contains(".")) {
                    x--;
                }
            }
            String[] rStr = new String[x];
            x = 0;
            for (String s : f.list()) {
                if (!s.contains(".")) {
                    rStr[x] = s;
                    x++;
                }
            }
            return rStr;
        }
        return null;
    }
}
