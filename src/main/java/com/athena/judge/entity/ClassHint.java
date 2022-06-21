package com.athena.judge.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "table_advice")
public class ClassHint implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_advice;
	
	String advice;
	String date;
	String nickname_user;
	int id_user;
	int id_problem;
	
	public String getNickname_user() {
		return nickname_user;
	}

	public void setNickname_user(String nickname_user) {
		this.nickname_user = nickname_user;
	}

	public int getId_advice() {
		return id_advice;
	}

	public void setId_advice(int id_advice) {
		this.id_advice = id_advice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_problem() {
		return id_problem;
	}

	public void setId_problem(int id_problem) {
		this.id_problem = id_problem;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public ClassHint() {
		//super();
	}

	
	@Override
	public String toString() {
		return "ClassHint [id_advice=" + id_advice + ", advice=" + advice + ", date=" + date + ", nickname_user="
				+ nickname_user + ", id_user=" + id_user + ", id_problem=" + id_problem + "]";
	}

	public String getCurrentTime() {
		 	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");  
		    Date date = new Date();  
		    String date1 = formatter.format(date).toString();
		   return date1;
	}
	
	
}
