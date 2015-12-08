/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Account;

import BDPuntoVentaManuel.ABSTRACT.IUsuario;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IuserExtends;
import BDPuntoVentaManuel.FACTORY.FactoryLogin;
import BDPuntoVentaManuel.MODEL.Usuario;

/**
 *
 * @author manuel
 */
public class Process {
    
    
    public Process()
    {
        this.startControlers();
    }
            
    
    private void startControlers()
    {   
        ctrUsuario=new FactoryLogin().getInstanceAbstract();
        ctrUsuarioExtends=new FactoryLogin().getInstanceExtends();
        
    }
    
    public Usuario Login(String Nombre, String Password)
    {
        Usuario item=ctrUsuarioExtends.findUsuario(Nombre, Password);
        if(item!=null)
        {
            return item;
        }
        
        return null;
    }
    
    //Controlador
    IUsuario ctrUsuario;
    IuserExtends ctrUsuarioExtends;
}
