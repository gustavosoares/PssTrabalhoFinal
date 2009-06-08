package com.pss.features.ativos.relacionamento.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.pss.core.model.Ativo;


@Entity
@Table(name = "relaciona_ativo", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@NamedQueries( {
		@NamedQuery(name = "RelacionamentoAtivo.listAll", query = "from RelacionamentoAtivo a"),
		@NamedQuery(name = "RelacionamentoAtivo.findByAtivoIdPai", query = "from RelacionamentoAtivo a where a.ativoIdPai = ?1"),
		@NamedQuery(name = "RelacionamentoAtivo.findByAtivoIdFilho", query = "from RelacionamentoAtivo a where a.ativoIdFilho = ?1"),
		@NamedQuery(name = "RelacionamentoAtivo.findByRelacionamento", query = "from RelacionamentoAtivo a where a.ativoIdPai = ?1 and a.ativoIdFilho = ?2") })

public class RelacionamentoAtivo {
	
	@Id
	@GeneratedValue
	@OrderBy("id")
	private Integer id;
	
	@Column(nullable = false)
	private Integer ativoIdPai;
	
	@Column(nullable = false)
	private Integer ativoIdFilho;
		
	public RelacionamentoAtivo() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAtivoIdPai() {
		return ativoIdPai;
	}

	public void setAtivoIdPai(Integer ativoIdPai) {
		this.ativoIdPai = ativoIdPai;
	}

	public Integer getAtivoIdFilho() {
		return ativoIdFilho;
	}

	public void setAtivoIdFilho(Integer ativoIdFilho) {
		this.ativoIdFilho = ativoIdFilho;
	}

	@Override
	public String toString() {
		return "Ativo[id=" + this.id + ", AtivoIdPai="
				+ this.ativoIdPai + ", AtivoIdFilho=" + this.ativoIdFilho + "]";
	}

	
}
