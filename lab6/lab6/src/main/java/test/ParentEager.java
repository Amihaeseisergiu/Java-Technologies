package test;

import abstraction.AbstractEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "ParentEager")
@Table(name = "parent_eager")
public class ParentEager extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @OneToMany(mappedBy = "parentEager", fetch = FetchType.EAGER)
    Set<Child> children = new HashSet<>();

    public ParentEager()
    {
        
    }
    
    public ParentEager(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }
    
}
