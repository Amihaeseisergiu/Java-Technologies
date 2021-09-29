/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amihaeseisergiu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author amiha
 */
public class ProjectWriter {
    
    private static final ProjectWriter projectWriter = new ProjectWriter();

    private ProjectWriter() {
    }

    private void writeData(String text, int value)
    {
        try {
            File repository = new File("D:\\Facultate\\Tehnologii Java\\lab1\\repository.txt");
            if(!repository.exists())
            {
                repository.createNewFile();
            }
            
            try(FileWriter fileWriter = new FileWriter(repository, true))
            {
                fileWriter.write(LocalDateTime.now() + " ");
                fileWriter.flush();
                
                for (int i = 1; i <= value; i++) {
                    fileWriter.write(text);
                    fileWriter.flush();
                }
                fileWriter.write("\n");
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
    
    public synchronized void writeSynchronous(String text, int value) {
        writeData(text, value);
    }
    
    public void writeAsynchronous(String text, int value)
    {
        writeData(text, value);
    }
    
    public synchronized void writeServerLog(HttpServletRequest request)
    {
        try {
            File repository = new File("D:\\Facultate\\Tehnologii Java\\lab1\\server.log");
            if(!repository.exists())
            {
                repository.createNewFile();
            }
            
            try(FileWriter fileWriter = new FileWriter(repository, true))
            {
                fileWriter.write("HTTP Method Used: " + request.getMethod() + " " +
                        "IP Address: " + request.getRemoteAddr() + " " +
                        "User agent: " + request.getHeader("user-agent") +
                        "User Language(s): " + Collections.list(request.getLocales()).toString() + " " +
                        "Request Parameter(s): " + Collections.list(request.getParameterNames()).toString()
                        + "\n");
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
    }

    public static ProjectWriter getInstance() {
        return projectWriter;
    }
}
