package model;

import abstraction.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bibliographies")
public class Bibliography extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @JoinColumn(name = "written_test_id", nullable = false)
    @ManyToOne
    WrittenTest writtenTest;
    
    public Bibliography()
    {
        
    }
    
    public Bibliography(String name)
    {
        this.name = name;
    }
    
    public Bibliography(String name, WrittenTest writtenTest)
    {
        this.name = name;
        this.writtenTest = writtenTest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WrittenTest getWrittenTest() {
        return writtenTest;
    }

    public void setWrittenTest(WrittenTest writtenTest) {
        this.writtenTest = writtenTest;
    }
    
}
