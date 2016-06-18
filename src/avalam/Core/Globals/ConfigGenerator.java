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

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generate a config file if it not exists.
 * The class do not override existing files.
 * If user encounters any problem with config file he can delete it.
 * @author Team 7
 */
public class ConfigGenerator {

    /**
     * Generate a Config file (./ressources/config/config.xml)
     */
    public static void generate() {
        if (!Files.exists(Paths.get("./ressources/config/config.xml"))) {
            try (PrintWriter writer = new PrintWriter("./ressources/config/config.xml", "UTF-8")) {
                writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<config>\n\t<Langue>fr</Langue>\n\t<FullScreen>Oui</FullScreen>\n\t<Theme>Default</Theme>\n\t<Son>Non</Son>\n</config>");
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                System.out.println("Error - " + ConfigGenerator.class.toString());
                Logger.getLogger(ConfigGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
