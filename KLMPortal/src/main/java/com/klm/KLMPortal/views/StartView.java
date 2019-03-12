package com.klm.KLMPortal.views;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@Route(AbstractView.STARTSVIEW)
@HtmlImport("frontend://styles/shared-styles.html")
public class StartView extends AbstractView implements RouterLayout {

	private static final long serialVersionUID = 1L;

	@Override
	public void setViewStyle() {
		setAlignItems(Alignment.CENTER);
		setHeight("100%");
		setClassName("startViewBackground");
	}

	@Override
	public void setRouteLinks() {
		RouterLink startRoute = new RouterLink(AbstractView.STARTSVIEW, StartView.class);
		startRoute.getStyle().set("color", "burlywood");
		RouterLink filmsRoute = new RouterLink(AbstractView.FILMSVIEW, MoviesView.class);
		filmsRoute.getStyle().set("color", "burlywood");
		RouterLink musicRoute = new RouterLink(AbstractView.MUSICSVIEW, MusicView.class);
		musicRoute.getStyle().set("color", "burlywood");
		RouterLink infoRoute = new RouterLink(AbstractView.GEN_INFO, InfoView.class);
		infoRoute.getStyle().set("color", "burlywood");
		
		getRouteLayout().add(startRoute);
		getRouteLayout().add(filmsRoute);
		getRouteLayout().add(musicRoute);
		getRouteLayout().add(infoRoute);
	}

	@Override
	public void buildMainLayout() {
		// TODO Auto-generated method stub
		
	}
	

	
}
