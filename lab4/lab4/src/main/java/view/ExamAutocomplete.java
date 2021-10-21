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
public class ExamAutocomplete extends Autocompletable implements Serializable {
    
    @Inject
    ExamView examView;
    
    @Override
    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> examList = new ArrayList<>();
        
        for (Exam e : examView.getExams()) {
            if(e.getName().toLowerCase().startsWith(queryLowerCase))
            {
                examList.add(e.getName());
            }
        }
        
        return examList;
    }

    @Override
    public void onItemSelect(SelectEvent<String> event) {
        for(Exam e : examView.getExams())
        {
            if(e.getName().equals(event.getObject()))
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
