package view;

import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class GrowlView {
    
    private static ResourceBundle getBundle()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, "msg");
    }
    
    private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public static void showEntityMessage(String className, String action, boolean success)
    {
        ResourceBundle bundle = getBundle();
        
        if(success)
        {
            String title = bundle.getString("growlInfoTitle");
            String body = bundle.getString("growl" + className + action);
            addMessage(FacesMessage.SEVERITY_INFO, title, body);
        }
        else
        {
            String title = bundle.getString("growlInfoTitle");
            String body = bundle.getString("growl" + className + action + "Fail");
            addMessage(FacesMessage.SEVERITY_ERROR, title, body);
        }
    }
    
    public static void testingStarted()
    {
        ResourceBundle bundle = getBundle();
        
        String title = bundle.getString("growlInfoTitle");
        String body = bundle.getString("growlTestingStarted");
        addMessage(FacesMessage.SEVERITY_INFO, title, body);
    }
    
    public static void testingFinished(double seconds)
    {
        ResourceBundle bundle = getBundle();
        
        String title = bundle.getString("growlInfoTitle");
        String body = bundle.getString("growlTestingFinished") + seconds + " " + bundle.getString("seconds");
        addMessage(FacesMessage.SEVERITY_INFO, title, body);
    }
}
