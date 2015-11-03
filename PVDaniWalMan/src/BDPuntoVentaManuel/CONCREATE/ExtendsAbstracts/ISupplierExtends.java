/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts;

import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Supplier;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ISupplierExtends {

    List<Supplier> FindDataByCategoriaId(Catcategoria categoria);

    EntityManager getEntityManager();
    
}
