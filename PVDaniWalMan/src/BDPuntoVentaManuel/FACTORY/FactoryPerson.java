/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPersona;
import BDPuntoVentaManuel.CONCREAT.PersonaJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPerson {
    private static FactoryPerson factory;
    
    static {
        try {
            factory=FactoryPerson.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPerson getInstance()
    {   
        return factory;
    }
    
    public IPersona getInstanceAbstract()
    {
        try{
            PersonaJpaController person= new PersonaJpaController();
            return (IPersona) person;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryPerson.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
