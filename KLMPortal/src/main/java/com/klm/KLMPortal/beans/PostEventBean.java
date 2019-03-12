package com.klm.KLMPortal.beans;

import java.time.LocalDate;

public class PostEventBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private LocalDate eventSetDate;
	private Boolean received;
	
	public PostEventBean() {
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

	public LocalDate getEventSetDate() {
		return eventSetDate;
	}

	public void setEventSetDate(LocalDate eventSetDate) {
		this.eventSetDate = eventSetDate;
	}

	public Boolean getReceived() {
		return received;
	}

	public void setReceived(Boolean received) {
		this.received = received;
	}
	

	
}
