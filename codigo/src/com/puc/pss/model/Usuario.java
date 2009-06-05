package com.puc.pss.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import sun.misc.BASE64Encoder;

@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "email" }) })
@NamedQueries({
		@NamedQuery(name = "Usuario.listAll", query = "from Usuario a"),
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
		return "Usuario[id=" + this.id + ", Nome="
				+ this.nome + ", Email=" + this.email + "]";
	}
	
	private String encriptar(String plainText) {
		byte[] sbe = null;
		MessageDigest md5;

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Nao foi possivel criptografar o " + plainText, e);
		}

		md5.update(plainText.getBytes());
		sbe = md5.digest();

		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(sbe);
	}
	
}
