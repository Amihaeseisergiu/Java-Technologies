package converter;

import entity.Role;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import repository.RoleRepository;

@Named
@RequestScoped
public class RoleConverter implements Converter<List<Role>> {

    @Inject
    RoleRepository roleRepository;

    @Override
    public List<Role> getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                List<Role> roles = new ArrayList<>();
                roles.add(roleRepository.findById(Long.parseLong(value)));
                
                return roles;
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
    public String getAsString(FacesContext context, UIComponent component, List<Role> value) {
        if (value != null && value.size() == 1) {
            return String.valueOf(value.get(0).getId());
        }
        else {
            return null;
        }
    }
}