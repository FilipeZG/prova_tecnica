package br.com.dbserver.converter;


import br.com.dbserver.model.Restaurant;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "restaurantConverter")
public class RestaurantConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String info) {
        if (info == null || info.isEmpty()){
            return null;
        }

        String[] restaurantInfo = info.split("-");
        int id = Integer.parseInt(restaurantInfo[0]);
        String name = restaurantInfo[1];

        return new Restaurant(id, name);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        if(obj != null && obj instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) obj;
            return restaurant.getId() + "-" + restaurant.getName();

        }

        return "";
    }

}
