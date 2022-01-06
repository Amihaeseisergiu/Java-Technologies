package abstraction;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity<ID extends Serializable> implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Basic(optional = false)
    @Column(name = "id")
    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    
}