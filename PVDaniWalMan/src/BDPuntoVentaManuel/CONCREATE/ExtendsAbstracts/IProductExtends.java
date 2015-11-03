/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts;

import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Product;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IProductExtends {

    List<Product> findDatadefault();
    List<Product> FindDataByFirstLetter(String leter);
    List<Product> FindDataByCategoriaId(Catcategoria categoria);

    EntityManager getEntityManager();
    Product findProductByCode(String strClave);
    
}
