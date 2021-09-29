/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amihaeseisergiu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author amiha
 */
public class ProjectReader {
    
    public static String constructRepositoryHtml()
    {
        List<Pair<String, String>> lines = new ArrayList<>();
        
        try (BufferedReader bufferedReader = 
                new BufferedReader(new FileReader("D:\\Facultate\\Tehnologii Java\\lab1\\repository.txt"))) {
            
            /*
                TreeMap nu ar merge deoarece trebuie sa sortez dupa cheie si
                pot avea cheie duplicat. Ar merge daca as putea sorta dupa timestamp
            */
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
               String[] keyValue = line.split(" ", 2);
               
               if(keyValue.length > 1)
               {
                   lines.add(new Pair(keyValue[0], keyValue[1]));
               }
               else
               {
                   lines.add(new Pair(keyValue[0], ""));
               }
            }
            
            Collections.sort(lines, new Comparator<Pair<String, String>>() {
                @Override
                public int compare(final Pair<String, String> o1, final Pair<String, String> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        StringBuilder ret = new StringBuilder();
        ret.append("<h1>Repository ordered by key: </h1>");
        
        for(Pair<String, String> p : lines)
        {
            ret.append("<h2>").append(p.getKey()).append(" ").append(p.getValue()).append(" </h2>");
        }
        
        return ret.toString();
    }
}
