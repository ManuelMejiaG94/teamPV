/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IContact;
import BDPuntoVentaManuel.CONCREAT.ContactoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryContact {
    private static FactoryContact factory;
    
    static {
        try {
            factory=FactoryContact.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryContact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryContact getInstance()
    {   
        return factory;
    }
    
    public IContact getInstanceAbstract()
    {
        try{
            ContactoJpaController Contact= new ContactoJpaController();
            return (IContact) Contact;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryContact.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
