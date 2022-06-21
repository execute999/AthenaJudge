package com.athena.judge.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "table_user")
public class ClassUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_user;
	
	private String email_address;
	private String password;
	private String nickname;
	private Integer solved = 0;
	private String img;
	
	@Column(name="enabled", columnDefinition="default 'true'")
	private Boolean enabled = true;

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSolved() {
		return solved;
	}

	public void setSolved(Integer solved) {
		this.solved = solved;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		//this.enabled = enabled;
	}

	public ClassUser() {
		//super();
	}
	
	@Override
	public String toString() {
		return "Class_User [id_user=" + id_user + ", email_address=" + email_address + ", password=" + password
				+ ", nickname=" + nickname + ", solved=" + solved + ", img=" + img + ", enabled=" + enabled + "]";
	}

}
