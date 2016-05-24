/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author sazeratj
 */
public class ThemesLister {

    public static String[] listThemes() {
        Path p = Paths.get("./ressources/Themes");
        if (Files.exists(p) && Files.isDirectory(p)) {
            File f = new File("./ressources/Themes");
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
