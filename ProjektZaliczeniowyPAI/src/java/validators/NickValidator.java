/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import hibernate.Person;
import hibernate.query.PersonHelper;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Marcin
 */

@FacesValidator("validators.NickValidator")
public class NickValidator implements Validator {
    
    PersonHelper helper = new PersonHelper();
    
    

    public NickValidator() {
    }

    
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       
        boolean ifExists = false;
      
        
        List<Person> persons = helper.getPersons();
        for( Person item : persons)
        {
            if(value.toString().equals(item.getNick()))
                ifExists = true;
        }
        
        
        if(ifExists)
        {
            FacesMessage msg = new FacesMessage("Wystąpił błąd walidacji nick",
            "Istnieje już taki Nick");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        
    }

    
    

}



