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