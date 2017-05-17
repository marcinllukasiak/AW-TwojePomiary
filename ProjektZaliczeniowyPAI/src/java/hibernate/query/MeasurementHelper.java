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
import java.util.Set;
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
    
    public List<Measurement> getMeasurements(String nickUser) {
        //pogranie persony o nicku i przepisanie pomiarów
        Query queryP = session.createQuery("FROM Person where nick = :nick");
        
        queryP.setParameter("nick",nickUser);
        Person p = (Person) queryP.uniqueResult();
        
        int idPers=p.getId();
        
        System.out.println("ID PERSON PRZEKAZANE>>>>>>>>:"+idPers);
        
        Query query = session.createQuery("FROM Measurement where person = :idP");
        
        query.setParameter("idP",p);
//        query.setParameter("idP","1");
        List<Measurement> m = query.list();
//            List m = p.getMeasurements();

        return m;
    }
    
    public void saveOrUpdate(Measurement m) {
        
        try{
        Transaction tx = this.session.getTransaction();
        tx.begin();
        this.session.saveOrUpdate(m);
        tx.commit(); 
        }catch(Exception e){
            System.out.println("OPERACJA NIE POWIODLA SIE <<<<<<<<<"+e);
        }
        
    }

    public void deleteMeasure(Measurement m) {
        
        try{
        Transaction tx = this.session.getTransaction();
        tx.begin();
        this.session.delete(m);
        tx.commit(); 
        }catch(Exception e){
            System.out.println("EXCEPTION NIE UDAŁO SIE USUNAC POMIARU<<<<<<<<<"+e);
        }
        
    }
    
    
   
    
    
    
}
