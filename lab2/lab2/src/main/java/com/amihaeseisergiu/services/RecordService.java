package com.amihaeseisergiu.services;

import com.amihaeseisergiu.models.Record;
import com.amihaeseisergiu.models.ServerRecords;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amiha
 */
public class RecordService {
    
    public static List<Record> readRecords()
    {
        return ServerRecords.getInstance().getRecords();
    }
    
    public static void writeRecord(Record record)
    {
        ServerRecords.getInstance().addRecord(record);
    }
}
