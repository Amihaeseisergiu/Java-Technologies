package repository;

import abstraction.DataRepository;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import model.Bibliography;

@Named
@ApplicationScoped
@Transactional
public class BibliographyRepository extends DataRepository<Bibliography, Long> implements Serializable {
    
    public BibliographyRepository()
    {
        super(Bibliography.class, false);
    }
}
