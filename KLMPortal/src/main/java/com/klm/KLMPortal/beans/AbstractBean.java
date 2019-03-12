package com.klm.KLMPortal.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Timestamp m_updatedDate;
	
	public AbstractBean() {
	}

	public Timestamp getUpdatedDate() {
		return m_updatedDate;
	}

	public void setUpdatedDate(Timestamp _updatedDate) {
		this.m_updatedDate = _updatedDate;
	}
	
}
