/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.query;

import hibernate.HibernateUtil;
import hibernate.Measurement;
import hibernate.Person;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Marcin
 */
public class MeasurementHelper {
    
        Session session = null;
    
    public MeasurementHelper() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List<Measurement> getMeasurements() {
        List<Measurement> m = session.createQuery("from Measurement").list();
        return m;
    }
    public void saveOrUpdate(Measurement m) {
        
        try{
        Transaction tx = this.session.getTransaction();
        tx.begin();
        this.session.saveOrUpdate(m);
        tx.commit(); 
        }catch(Exception e){
            System.out.println("EXCEPTION <<<<<<<<<"+e);
        }
        
    }

   
    
    
    
}
