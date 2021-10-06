package com.amihaeseisergiu.models;

public class Record {
    
    private String category;
    private String word;
    private String definition;
    
    public Record(String category, String word, String definition)
    {
        this.category = category;
        this.word = word;
        this.definition = definition;
    }
    
    @Override
    public String toString()
    {
        return category + " " + word + " " + definition;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * @param definition the definition to set
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
