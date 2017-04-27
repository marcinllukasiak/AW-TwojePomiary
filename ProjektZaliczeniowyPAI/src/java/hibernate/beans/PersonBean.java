/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.beans;

import hibernate.Person;
import hibernate.query.PersonHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Marcin
 */
@Named(value = "personBean")
@SessionScoped
public class PersonBean implements Serializable {

    /**
     * Creates a new instance of PersonBean
     */
     PersonHelper helper;
     private Person person = new Person();

    public PersonBean() {
        helper = new PersonHelper();
    }
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public String register(){
        
        
        //walidacja ?
        
        helper.saveOrUpdate(person);
        this.person = new Person();
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
