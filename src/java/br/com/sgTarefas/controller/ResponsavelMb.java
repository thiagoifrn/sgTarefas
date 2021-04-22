
package br.com.sgTarefas.controller;

import br.com.sgTarefas.DAO.Dao;
import br.com.sgTarefas.model.Responsavel;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author thiag
 */
@ManagedBean(name = "responsavelMb")
@ViewScoped
public class ResponsavelMb implements Serializable{
    private Responsavel responsavel = new Responsavel();
    private String idEditarResponsavel;
  
     public ResponsavelMb() {
        this.responsavel = new Responsavel();
    }
 
    
    public List responsavelListFromDb() {
        return Dao.getAllResponsavelDetails();        
    }
 
    
    public String addNewResponsavel() {
        Dao.novoResponsavel( this.responsavel);
        return "listResponsavel.xhtml?faces-redirect=true";         
    }
 
  
    public String deleteResponsavelById(int responsavelId) {      
        Dao.removerRespoonsavel(responsavelId);        
       return "listResponsavel.xhtml?faces-redirect=true";
    }
 
  
    public String editResponsavelDetailsById() {
       idEditarResponsavel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedResponsaveId");     
       return "editarResponsavel.xhtml";
    }
 

    public String updateResponsavelDetails(ResponsavelMb responsavelMb) {
        Dao.atualizarResponsavelDetails(Integer.parseInt(responsavelMb.getIdEditarResponsavel()), responsavel.getNome());        
       FacesContext.getCurrentInstance().addMessage("editResponsavelForm:responsavelId", new FacesMessage("Responsavel  #" + responsavel.getNome() + " Atualizado com sucesso"));
        return "editarResponsavel.xhtml";
    }

      public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public String getIdEditarResponsavel() {
        return idEditarResponsavel;
    }

    public void setIdEditarResponsavel(String idEditarResponsavel) {
        this.idEditarResponsavel = idEditarResponsavel;
    }

    
}
