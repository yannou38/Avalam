/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.File_IO;

import avalam_s6.Exceptions.GridSizeException;
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
        this.path = "./ressources/Levels/"+s;
    }
    
    public String readLevel() throws IOException,GridSizeException  {
        if (this.grid==null) {
            byte[] encoded = Files.readAllBytes(Paths.get(this.path));
            this.grid = new String(encoded, StandardCharsets.UTF_8);
        }
        if (this.grid.length() == 81 || this.grid.length() == 9) {            
            return this.grid;
        } else
            throw new GridSizeException(0);
    }
}
