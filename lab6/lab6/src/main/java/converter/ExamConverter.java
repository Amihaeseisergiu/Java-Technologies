package converter;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import model.Exam;
import view.ExamView;

@Named
@RequestScoped
public class ExamConverter implements Converter<Exam> {

    @Inject
    ExamView examView;

    @Override
    public Exam getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                Optional<Exam> exam = examView.getExams().stream().filter(e -> e.getId().equals(Long.parseLong(value))).findFirst();
                
                if(exam.isPresent())
                {
                    return exam.get();
                }
                
                return null;
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not valid"));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Exam value) {
        if (value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
    }
}
