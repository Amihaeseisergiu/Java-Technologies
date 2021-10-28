package view;

import abstraction.Autocompletable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Exam;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named
@RequestScoped
public class ExamAutocomplete extends Autocompletable<Exam> implements Serializable {
    
    @Inject
    ExamView examView;
    
    @Override
    public List<Exam> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Exam> examList = new ArrayList<>();
        
        for (Exam e : examView.getExams()) {
            if(e.getName().toLowerCase().startsWith(queryLowerCase))
            {
                examList.add(e);
            }
        }
        
        return examList;
    }

    @Override
    public void onItemSelect(SelectEvent<Exam> event) {
        for(Exam e : examView.getExams())
        {
            if(e.getId().equals(event.getObject().getId()))
            {
                examView.editRow(e);
                break;
            }
        }
        
        PrimeFaces current = PrimeFaces.current();
        current.ajax().update("dataEditForm");
        current.executeScript("PF('dataEditDialog').show();");
    }
    
}
