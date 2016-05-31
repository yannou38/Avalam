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
public class GridLister { 
    public static String[] listGrids() {
        Path p = Paths.get("./ressources/Levels");
        if (Files.exists(p) && Files.isDirectory(p)) {
            File f = new File("./ressources/Levels");
            String[] rStr = new String[f.list().length -1]; // Remove README
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