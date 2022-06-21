package com.athena.judge.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "table_problem")
public class ClassProblem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_problem;
	
	private String title;
	private String solution;
	private String document;
	private Integer solved_by;
	private boolean hidden;
	
	
	
	public boolean getHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public Integer getSolved_by() {
		return solved_by;
	}
	public void setSolved_by(Integer solved_by) {
		this.solved_by = solved_by;
	}
	public int getId_problem() {
		return id_problem;
	}
	public void setId_problem(int id_problem) {
		this.id_problem = id_problem;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
}
