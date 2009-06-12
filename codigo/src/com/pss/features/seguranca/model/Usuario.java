package com.pss.features.seguranca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.pss.core.facade.FacadeUtil;

@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "email" }) })
@NamedQueries({
		@NamedQuery(name = "Usuario.listAll", query = "from Usuario a"),
		@NamedQuery(name = "Usuario.findById", query = "from Usuario a where a.id = ?1"),
		@NamedQuery(name = "Usuario.findByEmailAndPassword", query = "from Usuario a where a.email = ?1 and a.senha = ?2") })
		
public class Usuario {
	
	@Id
	@GeneratedValue
	@OrderBy("id")
	private Integer id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	public Usuario() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = this.encriptar(senha);
	}

	@Override
	public String toString() {
		return "\nUsuario[id=" + this.id + ",\n Nome="
				+ this.nome + ",\n Email=" + this.email + "]";
	}
	
	private String encriptar(String plainText) {
		return FacadeUtil.encriptar(plainText);
	}
	
}
