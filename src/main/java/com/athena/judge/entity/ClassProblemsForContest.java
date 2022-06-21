package com.athena.judge.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "table_problems_for_contest")
public class ClassProblemsForContest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String solution;
	private String document;
	private Integer solved_by;
	private Integer idContest;
	
	public int getId() {
		return id;
	}
	public Integer getIdContest() {
		return idContest;
	}
	public void setIdContest(Integer idContest) {
		this.idContest = idContest;
	}
	public void setId(int id) {
		this.id = id;
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
	public Integer getSolved_by() {
		return solved_by;
	}
	public void setSolved_by(Integer solved_by) {
		this.solved_by = solved_by;
	}
	@Override
	public String toString() {
		return "ClassProblemsForContest [id=" + id + ", title=" + title + ", solution=" + solution + ", document="
				+ document + ", solved_by=" + solved_by + "]";
	}
	
	
}
