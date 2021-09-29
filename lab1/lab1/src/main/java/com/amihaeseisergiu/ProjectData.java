package com.amihaeseisergiu;

/**
 *
 * @author amiha
 */
public class ProjectData {
    String key;
    String value;
    
    public ProjectData(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
    
    public String getKey()
    {
        return this.key;
    }
    
    public String getValue()
    {
        return this.value;
    }
}
