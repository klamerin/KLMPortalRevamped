package com.klm.KLMPortal.views;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(StartView.GEN_INFO)
@HtmlImport("frontend://styles/shared-styles.html")
public class InfoView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setViewStyle() {
		setAlignItems(Alignment.CENTER);
		setHeight("100%");
		setClassName("infoViewBackground");
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
	
	
//	private Navigator nav = UI.getCurrent().getNavigator();
//	private HorizontalLayout navLayout = new HorizontalLayout();
//	private static final String FILMSVIEW = "films";
//	protected static final String MUSICSVIEW = "music";
//
//	private HorizontalLayout infoLayout = new HorizontalLayout();
//	private VerticalLayout eventLayout = new VerticalLayout();
//	private HorizontalSplitPanel panel = new HorizontalSplitPanel(infoLayout, eventLayout);
//
//	private Events eventView;
//	private Info infoView;
//
//
	

//	public InfoView() {
//		buildMainLayout();
//
//	private void buildMainLayout() {
//		setSizeFull();
//		setInfoLayout();
//		setEventLayout();
//		setNavLayout();
//		addComponent(panel);
//		setExpandRatio(navLayout, 1);
//		setExpandRatio(panel, 10);
//		setStyleName("generalInfoBackground");
//	}
//
//	private void setNavLayout() {
//		navLayout.setMargin(true);
//		navLayout.setSpacing(true);
//		Button startViewButton = new Button("Start View", new Button.ClickListener() {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				nav.navigateTo("");
//			}
//		});
//		navLayout.addComponent(startViewButton);
//		navLayout.setComponentAlignment(startViewButton, Alignment.TOP_CENTER);
//		Button filmsButton = new Button("Films", new Button.ClickListener() {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				nav.navigateTo(FILMSVIEW);
//			}
//		});
//		navLayout.addComponent(filmsButton);
//		navLayout.setComponentAlignment(filmsButton, Alignment.TOP_CENTER);
//
//		Button musicButton = new Button("Music", new Button.ClickListener() {
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				nav.navigateTo(MUSICSVIEW);
//			}
//		});
//		navLayout.addComponent(musicButton);
//		navLayout.setComponentAlignment(musicButton, Alignment.TOP_CENTER);
//		addComponent(navLayout);
//		setComponentAlignment(navLayout, Alignment.TOP_CENTER);
//	}
//
//	private void setInfoLayout() {
//		infoLayout.setMargin(true);
//		infoLayout.setSpacing(true);
//		
//		infoView = new Info();
//		infoLayout.addComponent(infoView);
//		infoLayout.setComponentAlignment(infoView, Alignment.TOP_RIGHT);
//	}
//	
//	private void setEventLayout() {
//		eventLayout.setMargin(true);
//		eventLayout.setSpacing(true);
//
//		eventView = new Events();
//		eventLayout.addComponent(eventView);
//		eventLayout.setComponentAlignment(eventView, Alignment.TOP_RIGHT);
//	}
//
//
//	@Override
//	public void enter(ViewChangeEvent event) {
//		Notification.show("HIIII, GEN MEN=)");
//	}
}
