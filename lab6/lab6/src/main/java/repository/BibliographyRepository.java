package repository;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.ejb.Stateless;
import model.Bibliography;

@Stateless
public class BibliographyRepository extends DataRepository<Bibliography, Long> implements Serializable {
    
    public BibliographyRepository()
    {
        super(Bibliography.class, false);
    }
}
