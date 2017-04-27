/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.query;

import hibernate.HibernateUtil;
import hibernate.Person;
import java.util.List;
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
        
        try{
        Transaction tx = this.session.getTransaction();
        tx.begin();
        this.session.saveOrUpdate(p);
        tx.commit(); 
        }catch(Exception e){
            System.out.println("EXCEPTION <<<<<<<<<"+e);
        }
        
    }

    public boolean isCorrectLogin(String nick,String password) {
        boolean returne = false;
        
        
        Query query = session.createQuery("FROM Person where nick = :nick");
        query.setParameter("nick",nick);
        Person p = (Person) query.uniqueResult();
        
       

        try{
            if(p.getPassword().equals(password)){
               returne = true; 
                System.out.println("WAZNE <<<<<<<<<<<<<<<<<<< PRAWDZIWE DANE <<<<<<<<<<<<<<<<");
            }
        }
        catch(Exception e){
            System.out.println("EXCEPTION <<<<<< "+e);
        }
        
        return returne;
    }
    

}
