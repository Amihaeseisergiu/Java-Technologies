package com.amihaeseisergiu.models;

import java.util.ArrayList;
import java.util.List;

public class ServerRecords {
    
    private static final ServerRecords serverData = new ServerRecords();
    private List<Record> records = new ArrayList<>();

    private ServerRecords() {
    }
    
    public static ServerRecords getInstance() {
        return serverData;
    }
    
    public void addRecord(Record record)
    {
        this.records.add(record);
    }
    
    public List<Record> getRecords()
    {
        return this.records;
    }
}
