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
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Marcin
 */
@Named(value = "authenticationBean")
@SessionScoped
public class AuthenticationBean implements Serializable {

   public static final String AUTH_KEY = "app.user.name";

    PersonHelper helper;
    private String nick;
    private String password;

    public AuthenticationBean() {
        helper = new PersonHelper();
    }
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String name) {
        this.nick = name;
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get(AUTH_KEY) != null;
    }

    public String login() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        //hashowanie hasła
        String pwd = this.password;
        String pwdMD5 = DatatypeConverter.printHexBinary( MessageDigest.getInstance("MD5").digest(pwd.getBytes("UTF-8")));
        this.password = pwdMD5;
        
        //sprawdzanie czy podane dane sa poprawne
        if(helper.isCorrectLogin(this.nick,this.password)){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, this.nick);
                //czyszczenie fieldow po zalogowaniu
                this.nick="";
        }
                this.password="";
        
        
        
//komunikat ze udalo sie albo nieudalo zalogowac
        
        return null; //  "secret" sciezka pozalogowaniu
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .remove(AUTH_KEY);
        return null;
    }
    
     
    
    
}
