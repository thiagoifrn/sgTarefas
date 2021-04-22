/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgTarefas.DAO;


import br.com.sgTarefas.model.Responsavel;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author thiag
 */
public class Dao implements Serializable{
    
   private static final String PERSISTENCE_UNIT_NAME = "sgTarefasPU";   
    private static final EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static final EntityTransaction transactionObj = entityMgrObj.getTransaction();
 
   
    @SuppressWarnings("unchecked")
    public static List getAllResponsavelDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT r FROM Responsavel r");
        List responsavelList = queryObj.getResultList();
        if (responsavelList != null && responsavelList.size() > 0) {           
            return responsavelList;
        } else {
            return null;
        }
    }
 
    public static void novoResponsavel(Responsavel resp) {
        if(!transactionObj.isActive()) {
            transactionObj.begin();
        }
 
        Responsavel responsavel = new Responsavel();
        responsavel.setId(getMaxResponsavelId());
        responsavel.setNome(resp.getNome());
        entityMgrObj.persist(responsavel);
        transactionObj.commit();
    }
 
    // Method To Delete The Selected School Id From The Database 
    public static void removerRespoonsavel(int responsavelId) {
        if (!transactionObj.isActive()) {
            transactionObj.begin();
        }
 
        Responsavel responsavel = new Responsavel();
        if(isResponsavelIdPresent(responsavelId)) {
            Long longRespon = new Long(responsavelId);
            responsavel.setId(longRespon);
            entityMgrObj.remove(entityMgrObj.merge(responsavel));
        }       
        transactionObj.commit();
        
    }
   
    public static void atualizarResponsavelDetails(int responsavelId, String nome) {
        if (!transactionObj.isActive()) {
            transactionObj.begin();
        }
 
        if(isResponsavelIdPresent(responsavelId)) {
            Query queryObj = entityMgrObj.createQuery("UPDATE Responsavel r SET r.name=:name WHERE r.id= :id");         
            queryObj.setParameter("id", responsavelId);
            queryObj.setParameter("nome", nome);
            int updateCount = queryObj.executeUpdate();
            if(updateCount > 0) {
                System.out.println("Record For Id: " + responsavelId + " Is Updated");
            }
        }
        transactionObj.commit();
       
    }
 
  
    private static Long getMaxResponsavelId() {
        Long maxResponsavelId = null;
        Query queryObj = entityMgrObj.createQuery("SELECT MAX(r.id)+1 FROM Responsavel r");
        if(queryObj.getSingleResult() != null) {
            maxResponsavelId =  (Long) queryObj.getSingleResult();
        }
        return maxResponsavelId;
    }
 
    private static boolean isResponsavelIdPresent(int responsavelId) {
        boolean idResult = false;
        Query queryObj = entityMgrObj.createQuery("SELECT r FROM Responsavel r WHERE r.id = :id");
        queryObj.setParameter("id", responsavelId);
      Responsavel selectResponsavelId = (Responsavel) queryObj.getSingleResult();
        if(selectResponsavelId != null) {
            idResult = true;
        }
        return idResult;
    }

    private Dao() {
    }
}
