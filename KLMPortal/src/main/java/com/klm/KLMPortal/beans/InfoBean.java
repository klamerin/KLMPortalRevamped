package com.klm.KLMPortal.beans;

public class InfoBean extends AbstractBean {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String infoKey;
	private String infoValue;
	private String comment;

	public InfoBean() {
		super();
	}

	public String getInfoKey() {
		return infoKey;
	}

	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}

	public String getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
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
}
