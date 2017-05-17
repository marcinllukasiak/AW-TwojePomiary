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


@FacesValidator("validators.EmailValidator")
public class EmailValidator implements Validator {
    private static boolean ifEdit = false;
    PersonHelper helper = new PersonHelper();
    
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
    }

    
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Pattern pattern;
        Matcher matcher;
        boolean ifExists = false;
        pattern = Pattern.compile(EMAIL_PATTERN);
        
       
        
        matcher = pattern.matcher(value.toString());
        if(!matcher.matches())
        {
            FacesMessage msg = new FacesMessage("Wystąpił błąd walidacji email",
            "Błędny format adresu e-mail");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
       
    }

    public static boolean isIfEdit() {
        return ifEdit;
    }

    public static void setIfEdit(boolean ifEdit) {
        EmailValidator.ifEdit = ifEdit;
    }
    
    

}
