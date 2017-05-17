/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import hibernate.Person;
import hibernate.query.PersonHelper;
import java.util.List;
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


@FacesValidator("validators.NumberValidatior")
public class NumberValidatior implements Validator {
    
    
    
    

    public NumberValidatior() {
    }

    
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       
        try{
            double num = Double.parseDouble(value.toString());
            System.out.println("Liczba "+value.toString()+" to DOUBLE "+num);
        }catch(Exception e){
            FacesMessage msg = new FacesMessage("Wystąpił błąd walidacji numeru",
            "Wpisana wartość nie jest liczbą");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
      
        
        
    }

    
    

}




