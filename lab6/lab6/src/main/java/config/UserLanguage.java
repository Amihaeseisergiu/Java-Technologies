package config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class UserLanguage implements Serializable {
    static final long serialVersionUID = 1L;
    
    Locale locale = Locale.ENGLISH;
    static final List<String> AVAILABLE_LOCALES = Arrays.asList("en", "ro");
    static Map<String, String> locales;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }
    
    public Map<String, String> getLocales()
    {
        locales = new LinkedHashMap<>();
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        
        for(String l : AVAILABLE_LOCALES)
        {
            String language = bundle.getString("language_" + l);
            locales.put(language, l);
        }
        return locales;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
