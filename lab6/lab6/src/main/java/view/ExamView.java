package view;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import model.Exam;
import repository.ExamRepository;
import abstraction.DatabaseInsert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import model.Bibliography;
import model.ProjectPresentation;
import model.RequiredResource;
import model.WrittenTest;
import repository.BibliographyRepository;
import repository.RequiredResourceRepository;

@Named
@ApplicationScoped
public class ExamView extends DatabaseInsert implements Serializable {
    
    String name;
    LocalTime startingTime;
    Integer duration;
    
    List<Exam> exams;
    
    Exam selectedForEdit;
    
    Integer examType = 0;
    List<String> examTypes;
    
    String bibliographyName;
    List<Bibliography> bibliographies = new ArrayList<>();
    
    String resourceName;
    List<RequiredResource> requiredResources = new ArrayList<>();
    
    String filterExamName;
    Boolean filterExamNameBool = false;
    String filterStudentName;
    Boolean filterStudentNameBool = false;
    LocalTime filterFrom;
    LocalTime filterTo;
    Boolean filterTimeBool = false;
    
    @EJB
    ExamRepository examRepository;
    
    @EJB
    BibliographyRepository bibliographyRepository;
    
    @EJB
    RequiredResourceRepository requiredResourceRepository;
    
    @Inject
    @Push(channel="push")
    PushContext push;
    
    @PostConstruct
    public void init() {
        ResourceBundle rb = this.getBundle();
        
        examTypes = new ArrayList<>();
        examTypes.add(rb.getString("writtenTest"));
        examTypes.add(rb.getString("projectPresentation"));
        
        exams = examRepository.findAll();
        
        Collections.sort(exams, (Exam o1, Exam o2) -> -o2.getId().compareTo(o1.getId()));
    }
    
    public List<Exam> getExams()
    {
        return exams;
    }
    
    public void editRow(Exam selected)
    {
        selectedForEdit = selected;
    }
    
    public void save()
    {
        examRepository.update(selectedForEdit);
        selectedForEdit = null;
        
        push.send("updateExams");
    }
    
    public void cancel()
    {
        selectedForEdit = null;
    }
    
    @Override
    public void insertInDatabase()
    {   
        if(examType == 0)
        {
            WrittenTest toInsert = new WrittenTest(name, startingTime, duration);
            examRepository.create(toInsert);
            
            for(Bibliography b : bibliographies)
            {
                b.setWrittenTest(toInsert);
                bibliographyRepository.create(b);
            }
        }
        else
        {
            ProjectPresentation toInsert = new ProjectPresentation(name, startingTime, duration);
            examRepository.create(toInsert);
            
            for(RequiredResource r : requiredResources)
            {
                r.setProjectPresentation(toInsert);
                requiredResourceRepository.create(r);
            }
        }
        
        exams = examRepository.findAll();
        
        this.name = null;
        this.startingTime = null;
        this.duration = 1;
        bibliographies = new ArrayList<>();
        requiredResources = new ArrayList<>();
        
        push.send("updateExams");
    }
    
    public void insertBibliography()
    {
        bibliographies.add(new Bibliography(bibliographyName));
        bibliographyName = null;
    }
    
    public void insertRequiredResource()
    {
        requiredResources.add(new RequiredResource(resourceName));
        resourceName = null;
    }
    
    public void applyFilters()
    {
        exams = examRepository.findByCriteria(filterExamNameBool ? filterExamName : null,
                filterStudentNameBool ? filterStudentName : null,
                filterTimeBool ? filterFrom : null, filterTimeBool ? filterTo : null);
    }

    private ResourceBundle getBundle()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, "msg");
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Exam getSelectedForEdit() {
        return selectedForEdit;
    }

    public List<String> getExamTypes() {
        ResourceBundle rb = getBundle();
        
        examTypes = new ArrayList<>();
        examTypes.add(rb.getString("writtenTest"));
        examTypes.add(rb.getString("projectPresentation"));
        
        return examTypes;
    }

    public String getExamType() {
        return examTypes.get(examType);
    }

    public void setExamType(String examType) {
        this.examType = examTypes.indexOf(examType);
    }

    public List<Bibliography> getBibliographies() {
        return bibliographies;
    }

    public String getBibliographyName() {
        return bibliographyName;
    }

    public void setBibliographyName(String bibliographyName) {
        this.bibliographyName = bibliographyName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<RequiredResource> getRequiredResources() {
        return requiredResources;
    }

    public String getFilterExamName() {
        return filterExamName;
    }

    public void setFilterExamName(String filterExamName) {
        this.filterExamName = filterExamName;
    }

    public String getFilterStudentName() {
        return filterStudentName;
    }

    public void setFilterStudentName(String filterStudentName) {
        this.filterStudentName = filterStudentName;
    }

    public Boolean getFilterExamNameBool() {
        return filterExamNameBool;
    }

    public void setFilterExamNameBool(Boolean filterExamNameBool) {
        this.filterExamNameBool = filterExamNameBool;
    }

    public Boolean getFilterStudentNameBool() {
        return filterStudentNameBool;
    }

    public void setFilterStudentNameBool(Boolean filterStudentNameBool) {
        this.filterStudentNameBool = filterStudentNameBool;
    }

    public LocalTime getFilterFrom() {
        return filterFrom;
    }

    public void setFilterFrom(LocalTime filterFrom) {
        this.filterFrom = filterFrom;
    }

    public LocalTime getFilterTo() {
        return filterTo;
    }

    public void setFilterTo(LocalTime filterTo) {
        this.filterTo = filterTo;
    }

    public Boolean getFilterTimeBool() {
        return filterTimeBool;
    }

    public void setFilterTimeBool(Boolean filterTimeBool) {
        this.filterTimeBool = filterTimeBool;
    }
    
}
