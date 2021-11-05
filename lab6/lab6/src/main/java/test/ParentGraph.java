package test;

import abstraction.AbstractEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "ParentGraph")
@Table(name = "parent_graph")
@NamedEntityGraph(name = "graph.ParentGraph.children", attributeNodes = @NamedAttributeNode("children"))
public class ParentGraph extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @OneToMany(mappedBy = "parentGraph", fetch = FetchType.LAZY)
    Set<Child> children = new HashSet<>();

    public ParentGraph()
    {
        
    }
    
    public ParentGraph(String name)
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
