package com.athena.judge.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "table_contest")
public class ClassContest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private int lenght;
	private String password;
	private String start;
	
	public String getCurrentTime() {
	 	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");  
	    Date date = new Date();  
	    String date1 = formatter.format(date).toString();
	    return date1;
	}
	public ClassContest() {
		this.start = getCurrentTime();
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public String getPassword() {
		return password;
	}

	
	
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ClassContest [id=" + id + ", Name=" + name + ", lenght=" + lenght + ", password=" + password + "]";
	}

}
