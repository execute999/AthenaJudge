package com.athena.judge.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "table_submission")
public class ClassSubmission implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_submission;
	
	private String answer;
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	private String status;
	
	public int getId_submission() {
		return id_submission;
	}

	public void setId_submission(int id_submission) {
		this.id_submission = id_submission;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId_problem() {
		return id_problem;
	}

	public void setId_problem(int id_problem) {
		this.id_problem = id_problem;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//@ManyToOne()
	//@JoinColumn(name = "id_problem")
	private int id_problem;

	//@ManyToOne()
	//@JoinColumn(name = "id_user")
	private int id_user;

	@Override
	public String toString() {
		return "Class_Submission [id_submission=" + id_submission + ", answer=" + answer + ", status=" + status
				+ ", id_problem=" + id_problem + ", id_user=" + id_user + "]";
	}

}
