package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PageController {
    
    public String homePage()
    {
        return "/home";
    }
    
    public String signUp()
    {
        return "/signup";
    }
    
    public String login()
    {
        return "/login";
    }
    
    public String timeFrame()
    {
        return "/pages/admin/timeframe";
    }
    
    public String viewDocuments()
    {
        return "/pages/admin/viewdocuments";
    }
    
    public String addDocuments()
    {
        return "/pages/author/adddocuments";
    }
}
