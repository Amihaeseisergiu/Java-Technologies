package view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HomeView {
    
    public String homePage()
    {
        return "home";
    }
    
    public String studentsPage()
    {
        return "students";
    }
    
    public String examsPage()
    {
        return "exams";
    }
}
