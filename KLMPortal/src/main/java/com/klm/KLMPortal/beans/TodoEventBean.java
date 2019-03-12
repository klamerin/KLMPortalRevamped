package com.klm.KLMPortal.beans;

import java.time.LocalDate;

public class TodoEventBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private LocalDate setDate;
	private LocalDate etaDate;
	private LocalDate doneDate;
	private String comment;
	private Boolean done;
	
	public TodoEventBean() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getSetDate() {
		return setDate;
	}

	public void setSetDate(LocalDate setDate) {
		this.setDate = setDate;
	}

	public LocalDate getEtaDate() {
		return etaDate;
	}

	public void setEtaDate(LocalDate etaDate) {
		this.etaDate = etaDate;
	}
	
	public LocalDate getDoneDate() {
		return doneDate;
	}


	public void setDoneDate(LocalDate doneDate) {
		this.doneDate = doneDate;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	
}
