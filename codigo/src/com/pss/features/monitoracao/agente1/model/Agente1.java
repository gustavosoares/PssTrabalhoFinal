package com.pss.features.monitoracao.agente1.model;

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
import com.pss.features.seguranca.model.Usuario;

@Entity
@Table(name = "agente1", uniqueConstraints = { @UniqueConstraint(columnNames = { "ativo_id", "usuario_id" }) })
@NamedQueries( {
		@NamedQuery(name = "Agente1.listAll", query = "from Agente1 a"),
		@NamedQuery(name = "Agente1.findByAtivo", query = "from Agente1 a where a.ativo = ?1"),
		@NamedQuery(name = "Agente1.findByUsuario", query = "from Agente1 a where a.usuario = ?1"),
		@NamedQuery(name = "Agente1.findByUsuarioEAtivo", query = "from Agente1 a where a.usuario = ?1 and a.ativo = ?2") })
		
public class Agente1 {

	@Id
	@GeneratedValue
	@OrderBy("id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ativo_id", nullable = false)
	private Ativo ativo;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	public Agente1() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "\nAgente1[id=" + this.id + ",\n Ativo=["
				+ this.ativo.getNome() + "] esta sendo observado por  Usuaio=[" + this.usuario.getEmail() + "]";
	}
	
}
