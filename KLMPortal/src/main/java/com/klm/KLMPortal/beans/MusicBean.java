package com.klm.KLMPortal.beans;

import java.time.LocalDate;

public class MusicBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private Integer id; 
	private String bandName;
	private String albumName;
	private String comment;
	private String portion;
	private LocalDate sessionDate;
	private Boolean concept;

	public MusicBean() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPortion() {
		return portion;
	}

	public void setPortion(String portion) {
		this.portion = portion;
	}

	public LocalDate getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(LocalDate sessionDate) {
		this.sessionDate = sessionDate;
	}

	public Boolean getConcept() {
		return concept;
	}

	public void setConcept(Boolean concept) {
		this.concept = concept;
	}

		

}
