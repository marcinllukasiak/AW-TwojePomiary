/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.beans;

import hibernate.Measurement;
import hibernate.Person;
import static hibernate.beans.AuthenticationBean.AUTH_KEY;
import hibernate.query.MeasurementHelper;
import hibernate.query.PersonHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcin
 */
@Named(value = "mesurementBean")
@SessionScoped
public class MeasurementBean implements Serializable {

    /**
     * Creates a new instance of MesurementBean
     */
    
   
    
     MeasurementHelper helper;
     PersonHelper phelper;
     private Measurement measure = new Measurement();
    
     
     
     
    public MeasurementBean() {
        helper = new MeasurementHelper();
        phelper = new PersonHelper(); // do przekazywania persony do pomiaru
    }

   
    
    
    

    public Measurement getMeasure() {
        return measure;
    }

    public void setMeasure(Measurement measure) {
        this.measure = measure;
    }
    
    
    public String addMeasurement(){
        
        
        //walidacja ?
        
        //ustawienie użytkowniaka
// pobieranie i ustawianie persony sesja
        String nick = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user.name");
        measure.setPerson(phelper.getPerson(nick));
        
        helper.saveOrUpdate(measure);
        this.measure = new Measurement();
//        EntityManager em = DBManager.getManager().createEntityManager();
//        em.getTransaction().begin();
//        person.setId(null);
//        em.persist(person);
//        em.getTransaction().commit();
//        em.close();
//        this.person = new Person();
        
        return null;
    }
    
}
