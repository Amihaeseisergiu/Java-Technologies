package test;

import abstraction.AbstractEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "ParentLazy")
@Table(name = "parent_lazy")
public class ParentLazy extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @OneToMany(mappedBy = "parentLazy", fetch = FetchType.LAZY)
    Set<Child> children = new HashSet<>();

    public ParentLazy()
    {
        
    }
    
    public ParentLazy(String name)
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
