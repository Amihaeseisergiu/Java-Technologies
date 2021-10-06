package com.amihaeseisergiu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final Logger logger = new Logger();

    private Logger() {
    }
    
    public synchronized void writeServerLog(String log)
    {
        try {
            File repository = new File("D:\\Facultate\\Tehnologii Java\\lab2\\server.log");
            if(!repository.exists())
            {
                repository.createNewFile();
            }
            
            try(FileWriter fileWriter = new FileWriter(repository, true))
            {
                fileWriter.write(log + "\n");
            }
          } catch (IOException e) {
            System.out.println("Error writing to file");
          }
    }

    public static Logger getInstance() {
        return logger;
    }
}
