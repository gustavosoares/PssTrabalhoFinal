package com.pss.features.ativos.relacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.pss.core.model.Ativo;


@Entity
@Table(name = "relaciona_ativo", uniqueConstraints = { @UniqueConstraint(columnNames = { "ativoPai_id", "ativoFilho_id" }) })
@NamedQueries( {
		@NamedQuery(name = "RelacionamentoAtivo.listAll", query = "from RelacionamentoAtivo a"),
		@NamedQuery(name = "RelacionamentoAtivo.findByRelacionamentoId", query = "from RelacionamentoAtivo a where a.id = ?1"),
		@NamedQuery(name = "RelacionamentoAtivo.findByAtivoPai", query = "from RelacionamentoAtivo a where a.ativoPai = ?1"),
		@NamedQuery(name = "RelacionamentoAtivo.findByAtivoFilho", query = "from RelacionamentoAtivo a where a.ativoFilho = ?1"),
		@NamedQuery(name = "RelacionamentoAtivo.findByAtivo", query = "from RelacionamentoAtivo a where a.ativoPai = ?1 or a.ativoFilho = ?1"),
		@NamedQuery(name = "RelacionamentoAtivo.findByRelacionamento", query = "from RelacionamentoAtivo a where a.ativoPai = ?1 and a.ativoFilho = ?2") })

public class RelacionamentoAtivo {
	
	@Id
	@GeneratedValue
	@OrderBy("id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ativoPai_id", nullable = false)
	private Ativo ativoPai;
	
	@ManyToOne
	@JoinColumn(name = "ativoFilho_id", nullable = false)
	private Ativo ativoFilho;
	
	public RelacionamentoAtivo() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ativo getAtivoPai() {
		return ativoPai;
	}

	public void setAtivoPai(Ativo ativoPai) {
		this.ativoPai = ativoPai;
	}

	public Ativo getAtivoFilho() {
		return ativoFilho;
	}

	public void setAtivoFilho(Ativo ativoFilho) {
		this.ativoFilho = ativoFilho;
	}

	@Override
/*	
	public String toString() {
		return "\nRelacionamentoAtivo[id=" + this.id + ",\n AtivoIdPai=["
				+ this.ativoPai + "] <===>  AtivoIdFilho=[" + this.ativoFilho + "]";
	}
*/
	public String toString() {
		
		return "\nRelacionamentoAtivo[AtivoIdPai="
				+ this.ativoPai.getId() + " <===>  AtivoIdFilho=" + this.ativoFilho.getId() + "]";
	}

	
}
