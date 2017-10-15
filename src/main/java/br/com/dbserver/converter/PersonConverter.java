package br.com.dbserver.converter;


import br.com.dbserver.model.Person;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "personConverter")
public class PersonConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return new Person(Integer.parseInt(id), null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object person) {
        if(person != null && person instanceof Person) {
            int id = ((Person) person).getId();
            return String.format ("%s", id);
        }

        return "";
    }

}
