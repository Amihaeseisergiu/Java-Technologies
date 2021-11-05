package test;

import abstraction.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Child")
@Table(name = "child")
public class Child extends AbstractEntity<Long> {
    
    @Column(name = "name")
    String name;
    
    @JoinColumn(name = "parent_eager_id")
    @ManyToOne
    ParentEager parentEager;
    
    @JoinColumn(name = "parent_lazy_id")
    @ManyToOne
    ParentLazy parentLazy;
    
    @JoinColumn(name = "parent_cache_id")
    @ManyToOne
    ParentCache parentCache;
    
    @JoinColumn(name = "parent_graph_id")
    @ManyToOne
    ParentGraph parentGraph;
    
    public Child()
    {
        
    }

    public Child(String name)
    {
        this.name = name;
    }
    
    public void addParentEager(ParentEager parentEager)
    {
        this.parentEager = parentEager;
        parentEager.getChildren().add(this);
    }
    
    public void addParentLazy(ParentLazy parentLazy)
    {
        this.parentLazy = parentLazy;
        parentLazy.getChildren().add(this);
    }
    
    public void addParentCache(ParentCache parentCache)
    {
        this.parentCache = parentCache;
        parentCache.getChildren().add(this);
    }
    
    public void addParentGraph(ParentGraph parentGraph)
    {
        this.parentGraph = parentGraph;
        parentGraph.getChildren().add(this);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParentEager getParentEager() {
        return parentEager;
    }

    public void setParentEager(ParentEager parentEager) {
        this.parentEager = parentEager;
    }

    public ParentLazy getParentLazy() {
        return parentLazy;
    }

    public void setParentLazy(ParentLazy parentLazy) {
        this.parentLazy = parentLazy;
    }

    public ParentCache getParentCache() {
        return parentCache;
    }

    public void setParentCache(ParentCache parentCache) {
        this.parentCache = parentCache;
    }

    public ParentGraph getParentGraph() {
        return parentGraph;
    }

    public void setParentGraph(ParentGraph parentGraph) {
        this.parentGraph = parentGraph;
    }
    
    
}
