/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts;

import BDPuntoVentaManuel.MODEL.Po;
import BDPuntoVentaManuel.MODEL.Podetail;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPoDetailExtends {

    List<Podetail> GetPoDetaiByPo(Po po);

    EntityManager getEntityManager();
    
}
