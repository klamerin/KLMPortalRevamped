package com.klm.KLMPortal.beans;

public class MonthlyEventBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private Integer amount;
	private String comment;
	
	public MonthlyEventBean() {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	
}
