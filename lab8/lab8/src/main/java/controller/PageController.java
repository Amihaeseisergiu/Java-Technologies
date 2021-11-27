package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PageController {
    
    public String homePage()
    {
        return "home";
    }
    
    public String signUp()
    {
        return "signup";
    }
    
    public String login()
    {
        return "login";
    }
    
    public String timeFrame()
    {
        return "timeframe";
    }
    
    public String viewDocuments()
    {
        return "viewdocuments";
    }
    
    public String addDocuments()
    {
        return "adddocuments";
    }
}
