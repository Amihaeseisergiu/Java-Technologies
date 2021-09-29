package com.amihaeseisergiu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author amiha
 */
public class DataWriter {
    
    private static final DataWriter dataWriter = new DataWriter();

    private DataWriter() {
    }
    
    public synchronized void log(boolean server, String text)
    {
        try {
            File data = null;
            
            if(server)
            {
                data = new File("D:\\Facultate\\Tehnologii Java\\lab1\\statistics\\server_stats.log");
            }
            else
            {
                data = new File("D:\\Facultate\\Tehnologii Java\\lab1\\statistics\\local_stats.log");
            }
            
            if(!data.exists())
            {
                data.createNewFile();
            }
            
            try(FileWriter fileWriter = new FileWriter(data, true))
            {
                fileWriter.write(text + "\n");
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
    }

    public static DataWriter getInstance() {
        return dataWriter;
    }
}

