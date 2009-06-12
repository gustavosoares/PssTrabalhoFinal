package com.pss.core.model;

import java.util.Date;

import java.text.SimpleDateFormat;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "ativo", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@NamedQueries( {
		@NamedQuery(name = "Ativo.listAll", query = "from Ativo a"),
		@NamedQuery(name = "Ativo.findById", query = "from Ativo a where a.id = ?1"),
		@NamedQuery(name = "Ativo.findAtivoByType", query = "from Ativo a where a.tipo = ?1") })

public class Ativo {
	
	@Id
	@GeneratedValue
	@OrderBy("id")
	private Integer id;
	
	@Column(nullable = false)
	private Integer tipo;

	@Column(nullable = false, length=1000)
	private String descricacao;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Date dataCriacao;
	
	private Date dataAlteracao;

	public static final int TIPO_SERVIDOR = 1;
	public static final int TIPO_APLICACAO = 2;
	public static final int TIPO_ROTEADOR = 3;
	
	public Ativo() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public String getDataCriacaoFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.dataCriacao);
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public String getDataAlteracaoFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.dataAlteracao);
	}
	
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	
	public String getDescricacao() {
		return descricacao;
	}

	public void setDescricacao(String descricacao) {
		this.descricacao = descricacao;
	}

	@Override
	public String toString() {
		return "\nAtivo[id=" + this.id + ",\n Nome="
				+ this.nome + ",\n Tipo=" + this.tipo + ",\n Data Criacao: " +this.dataCriacao +"]";
	}

	
}
