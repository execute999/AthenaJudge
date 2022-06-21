package com.athena.judge.entity;

public class ClassProblemSelected {
	
	private int idProblem;
	private String title;
	private String document;
	private boolean selected;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIdProblem() {
		return idProblem;
	}
	public void setIdProblem(int idProblem) {
		this.idProblem = idProblem;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
