package com.klm.KLMPortal.data.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.klm.KLMPortal.beans.GeneralEventBean;
import com.klm.KLMPortal.beans.InfoBean;
import com.klm.KLMPortal.beans.MonthlyEventBean;
import com.klm.KLMPortal.beans.PostEventBean;
import com.klm.KLMPortal.beans.TodoEventBean;

public interface IGeneralInfoDAO {
	
	public List<InfoBean> getAllInfos();
	
	public void addNewInfo(String infoKey, String infoValue, String comment);
	
	public void setInfoData(Integer infoId, String infoValue, String comment);
	
	public void deleteInfo(Integer infoId);
	
//	public ArrayList<EventBean> getAllEvents();
//	
//	public ArrayList<EventBean> getAllEventsByType(String eventType);
//	
//	
//	public void addNewEvent(String eventName, LocalDate eventSetLocalDate, LocalDate eventETALocalDate, String comment, String eventType);
//	
//	
//	public void setEventSetDate(Integer eventId, LocalDate eventSetLocalDate);
//	
//	public void setEventETADate(Integer eventId, LocalDate eventETALocalDate);
//	
//	public void deleteEvent(Integer eventId);
//	
//	public void setEventComment(Integer eventId, String comment);
//	
//	public void setEventData(Integer eventId, String eventName, LocalDate eventSetLocalDate, LocalDate eventETALocalDate, String comment, String eventType);
//	
//	public ArrayList<EventBean> getPostEventsNotReceived();
	
	
	//************************REFACTORE**********************************//
	
	public List<GeneralEventBean> getAllGeneralEvents();
	
	public void addNewGeneralEvent(String eventName, String eventDescription, String comment, LocalDate eventDate);
	
	public void updateGeneralEvent(Integer eventId, String eventName, String eventDescription, String comment, LocalDate eventDate);
	
	public void deleteGeneralEvent(Integer eventId);
	
	public List<MonthlyEventBean> getAllMonthlyEvents();
	
	public void addNewMonthlyEvent(MonthlyEventBean bean);
	
	public void updateMonthlyEvent(MonthlyEventBean bean);
	
	public void deleteMonthlyEvent(Integer eventId);
	
	public List<PostEventBean> getPostEvents(Boolean received);
	
	public void addNewPostEvent(PostEventBean bean);
	
	public void updatePostEvent(PostEventBean bean);
	
	public void deletePostEvent(Integer eventId);
	
	public ArrayList<TodoEventBean> getToDoEvents(Boolean done);
	
	public void addNewToDoEvent(TodoEventBean bean);
	
	public void updateToDoEvent(TodoEventBean bean);
	
	public void deleteToDoEvent(Integer eventId);
	
}
