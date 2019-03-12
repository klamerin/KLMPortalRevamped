package com.klm.KLMPortal.beans;

import java.time.LocalDate;

public class GeneralEventBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String eventName;
	private String eventDescription;
	private LocalDate eventDate;
	private String comment;
	
	public GeneralEventBean() {
		super();
	}
	

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	
}
