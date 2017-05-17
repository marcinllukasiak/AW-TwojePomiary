/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.query;

import hibernate.HibernateUtil;
import hibernate.Person;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class PersonHelper {
    Session session = null;
    
    public PersonHelper() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List<Person> getPersons() {
        List<Person> p = session.createQuery("from Person").list();
        return p;
    }
    
    public Person getPerson(String nick) {
        Query query = session.createQuery("FROM Person where nick = :nick");
        query.setParameter("nick",nick);
        Person p = (Person) query.uniqueResult();
        
        return p;
    }
    
    public void saveOrUpdate(Person p) {
        
        if(this.getPerson(p.getNick())==null){
            System.out.println("Person jest nulem czyli nie ma takiego goscia mozna dodawać");
            
            
            try{
        Transaction tx = this.session.getTransaction();
        tx.begin();
        this.session.saveOrUpdate(p);
        tx.commit(); 
        }catch(Exception e){
            System.out.println("Nie dodano do bazy <<<<<<<<<"+e);
        }
            
        }else{System.out.println(p);}
        
        
        
        
    }

    public boolean isCorrectLogin(String nick,String password) {
        boolean returne = false;
        
        System.out.println("NICK - "+nick);
        System.out.println("Password - "+password);
        Query query = session.createQuery("FROM Person where nick = :nick");
        query.setParameter("nick",nick);
        Person p = (Person) query.uniqueResult();
       
        
        System.out.println("Person - "+p);
       

        try{
            if(p.getPassword().equals(password)){
               returne = true; 
                System.out.println("WAZNE <<<<<<<<<<<<<<<<<<< PRAWDZIWE DANE <<<<<<<<<<<<<<<<");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wprowadzone dane są niepoprawne"));
            }
        }
        catch(Exception e){
            System.out.println("EXCEPTION <<<<<< "+e);
             
        }
        
        return returne;
    }
    

}
