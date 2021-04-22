package br.com.sgTarefas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thiag
 */
@Entity
@Table(name = "tarefa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarefa.findAll", query = "SELECT t FROM Tarefa t"),
    @NamedQuery(name = "Tarefa.findById", query = "SELECT t FROM Tarefa t WHERE t.id = :id"),
    @NamedQuery(name = "Tarefa.findByDatavencimento", query = "SELECT t FROM Tarefa t WHERE t.datavencimento = :datavencimento"),
    @NamedQuery(name = "Tarefa.findByDescricao", query = "SELECT t FROM Tarefa t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "Tarefa.findByPrioridade", query = "SELECT t FROM Tarefa t WHERE t.prioridade = :prioridade"),
    @NamedQuery(name = "Tarefa.findByStatus", query = "SELECT t FROM Tarefa t WHERE t.status = :status"),
    @NamedQuery(name = "Tarefa.findByTitulo", query = "SELECT t FROM Tarefa t WHERE t.titulo = :titulo")})
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static enum Prioridade {
        ALTA, MEDIA, BAIXA;
    }
	public static enum Situacao {
        EM_ANDAMENTO, CONCLUIDA;
    }
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "datavencimento")
    @Temporal(TemporalType.DATE)
    private Date datavencimento;
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;
    
    @Enumerated(EnumType.STRING)
	@Column(name = "prioridade")
	private Prioridade prioridade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Situacao situacao = Situacao.EM_ANDAMENTO;
    
    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;
    @JoinColumn(name = "responsavel_id", referencedColumnName = "id")
    @ManyToOne
    private Responsavel responsavelId;

    public Tarefa() {
    }

    public Tarefa(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Responsavel getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Responsavel responsavelId) {
        this.responsavelId = responsavelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarefa)) {
            return false;
        }
        Tarefa other = (Tarefa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgTarefas.model.Tarefa[ id=" + id + " ]";
    }
    
}
