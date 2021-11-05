package test;

import abstraction.AbstractEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Cacheable(true)
@Entity(name = "ParentCache")
@Table(name = "parent_cache")
public class ParentCache extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @OneToMany(mappedBy = "parentCache", fetch = FetchType.LAZY)
    Set<Child> children = new HashSet<>();

    public ParentCache()
    {
        
    }
    
    public ParentCache(String name)
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
