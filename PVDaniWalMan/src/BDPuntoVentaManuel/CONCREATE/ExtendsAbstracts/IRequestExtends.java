/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts;

import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IRequestExtends {
    EntityManager getEntityManager();
    int lastId();
    java.util.List<BDPuntoVentaManuel.MODEL.Request> RetriveRequestBySupplierAndStatus(BDPuntoVentaManuel.MODEL.Supplier supplier, int statusRequest);
    java.util.List<BDPuntoVentaManuel.MODEL.Request> RetriveRequestByFolio(int folio);
}
