package view;

import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class GrowlView {
    
    private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static void examAddedSuccess() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        
        String title = bundle.getString("growlInfoTitle");
        String body = bundle.getString("growlExamAdded");
        addMessage(FacesMessage.SEVERITY_INFO, title, body);
    }
    
    public static void examAddedFailure() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        
        String title = bundle.getString("growlErrorTitle");
        String body = bundle.getString("growlExamAddFail");
        addMessage(FacesMessage.SEVERITY_ERROR, title, body);
    }
}
