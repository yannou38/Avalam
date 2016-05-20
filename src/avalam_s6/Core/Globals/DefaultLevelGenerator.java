/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

import java.io.File;

/**
 *
 * @author sazeratj
 */
public class DefaultLevelGenerator {
    public static void generate() {
        File f = new File("./ressources/Levels/default");
        if(! f.exists()) { 
            // do something
        }
    }
}
