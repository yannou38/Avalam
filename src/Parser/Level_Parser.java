/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author sazeratj
 */
public class Level_Parser {
    String grid;
    String path;
    
    public Level_Parser(String s) {
        this.path = s;
    }
    
    public String readLevel() throws IOException  {
        if (this.grid==null) {
            byte[] encoded = Files.readAllBytes(Paths.get(this.path));
            this.grid = new String(encoded, StandardCharsets.UTF_8);
        }
        return grid;
    }
}
