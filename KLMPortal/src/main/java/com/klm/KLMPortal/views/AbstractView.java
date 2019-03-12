package com.klm.KLMPortal.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private HorizontalLayout m_routeLayout;
	
	public static final String STARTSVIEW = "";
	public static final String FILMSVIEW = "Films";
	public static final String GEN_INFO = "Info";
	public static final String MUSICSVIEW = "Music";
	
	public AbstractView() {
		init();
	}
	
	public void init() {
		m_routeLayout = new HorizontalLayout();
		add(m_routeLayout);
		
		setRouteLinks();
		setViewStyle();
		buildMainLayout();
	}

	public abstract void setViewStyle();

	public abstract void setRouteLinks();
	
	public abstract void buildMainLayout();
	
	public HorizontalLayout getRouteLayout() {
		return m_routeLayout;
	}
	
	
}
