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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
      private Measurement current;
     DataModel measurementsList;
     private String nick;
     
     
     
    public MeasurementBean() {
        helper = new MeasurementHelper();
        phelper = new PersonHelper(); // do przekazywania persony do pomiaru
        nick = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user.name");
        
    }

   public DataModel getMeasurementsList() {
//        if (measurementsList == null) {
//            measurementsList = new ListDataModel(helper.getMeasurements(this.nick));
//        }
            measurementsList = new ListDataModel(helper.getMeasurements(this.nick));

        return measurementsList;
    }
    public DataModel getMeasurementsListEdit() {


        return measurementsList;
    }

    public Measurement getCurrent() {
        return current;
    }
    public String returnml() {
        return "listOfMeasurements";
    }
    

    public void setCurrent(Measurement current) {
        this.current = current;
    }
    
    

    public Measurement getMeasure() {
        return measure;
    }

    public void setMeasure(Measurement measure) {
        this.measure = measure;
    }
    
    
    public String addMeasurement(){
        
        
        
        
        //ustawienie użytkowniaka
// pobieranie i ustawianie persony sesja
        
        measure.setPerson(phelper.getPerson(this.nick));
        
        helper.saveOrUpdate(measure);
        this.measure = new Measurement();
//        EntityManager em = DBManager.getManager().createEntityManager();
//        em.getTransaction().begin();
//        person.setId(null);
//        em.persist(person);
//        em.getTransaction().commit();
//        em.close();
//        this.person = new Person();
        
        return "listOfMeasurements";
    }
    
    public String editMeasurement(){
        
        
        //walidacja ?
        
        //ustawienie użytkowniaka
// pobieranie i ustawianie persony sesja
        
        current.setPerson(phelper.getPerson(this.nick));
        
        helper.saveOrUpdate(current);
        
        
        return "listOfMeasurements";
    }
    
    public String deleteMeasurement(){
        current = (Measurement) getMeasurementsListEdit().getRowData();
        helper.deleteMeasure(current);
        return "listOfMeasurements";
    }
    
    
     public String prepareView(){
        current = (Measurement) getMeasurementsListEdit().getRowData();
        return "viewMeasurement";
    }
    
    public String prepareEdit(){
        current = (Measurement) getMeasurementsListEdit().getRowData();
        return "editMesurement";
    }
    
    public Measurement getSelected() {
    if (current == null) {
        current = new Measurement();
        
    }
    return current;
    }
}
