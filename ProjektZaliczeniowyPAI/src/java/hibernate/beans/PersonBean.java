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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;
import javax.xml.bind.DatatypeConverter;

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
    
    public String register() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        
        
        //walidacja ?
        
        
        //hashowanie hasła
        String pwd = person.getPassword();
        String pwdMD5 = DatatypeConverter.printHexBinary( MessageDigest.getInstance("MD5").digest(pwd.getBytes("UTF-8")));
        person.setPassword(pwdMD5);
        
        System.out.println("PASWORD WPROWADOZNY "+person.getPassword());
        
        //zapisywanie
        helper.saveOrUpdate(person);
        this.person = new Person();

        
        return "login";
    }
    
}
