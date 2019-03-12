package com.klm.KLMPortal.views;

import java.time.LocalDate;
import java.util.Arrays;

import com.klm.KLMPortal.beans.MusicBean;
import com.klm.KLMPortal.data.DAOFactory;
import com.klm.KLMPortal.data.dao.IMusicDAO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.incubator.Autocomplete;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@Route(StartView.MUSICSVIEW)
@HtmlImport("frontend://styles/shared-styles.html")
public class MusicView extends AbstractView {

	private static final long serialVersionUID = 1L;

	
	private DAOFactory mysqlDAOFactory;
	private IMusicDAO musicDAO;

	/*
	 * —————————————————————————————————————————————————\
	 * 
	 * | -------------G U I — F I E L D S----------------|
	 * 
	 * —————————————————————————————————————————————————/
	 */

	private HorizontalLayout musicLayout;
	private HorizontalLayout buttonsLayout;
	private HorizontalLayout sessionsLayout;

	/*
	 * —————————————————————————————————————————————————\
	 * 
	 * | -------B U T T O N S — G U I — F I E L D S------|
	 * 
	 * —————————————————————————————————————————————————/
	 */

	private Grid<MusicBean> sessionsTable;
	private Dialog listeningSessionWindow;
	private FormLayout listeningSessionWindowLayout;
	private Autocomplete bandNameAutocompleteField;
	private Autocomplete albumNameAutocompleteField;
	private TextField bandNameTextField;
	private TextField albumNameTextField;
	private ComboBox<String> portionComboBox;
	private TextField commentTextField;
	private DatePicker dateField;
	private Checkbox conceptCheckBox;

	/*
	 * —————————————————————————————————————————————————\
	 * 
	 * | ------------D A T A — F I E L D S---------------|
	 * 
	 * —————————————————————————————————————————————————/
	 */
	
	
	@Override
	public void setViewStyle() {
		setAlignItems(Alignment.CENTER);
		setHeight("100%");
		setClassName("musicViewBackground");
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
		initView();
		add(musicLayout);
		
	}

	private void initView() {
		mysqlDAOFactory = DAOFactory.getMYSQLFactory();
		musicDAO = mysqlDAOFactory.getMusicDAO();
		
		
		musicLayout = new HorizontalLayout();
		musicLayout.setWidth("100%");
		
		buttonsLayout = new HorizontalLayout();
		setButtonsLayout();

		sessionsLayout = new HorizontalLayout();
		sessionsLayout.setWidth("50%");
		setSessionsTable();
		
		musicLayout.add(buttonsLayout);
		musicLayout.add(sessionsLayout);
		musicLayout.setFlexGrow(2, buttonsLayout);
		musicLayout.setFlexGrow(6, sessionsLayout);
		musicLayout.setSpacing(true);
	}


	
	private void setButtonsLayout() {
		buttonsLayout.add(new Button("Add session", event -> openCreateListeningSessionWindow()));
	}

	private void setSessionsTable() {
		sessionsTable = new Grid<MusicBean>(MusicBean.class);
		sessionsTable.setWidth("80%");
		sessionsTable.getStyle().set("opacity", "0.6");
		sessionsTable.getStyle().set("font-weight", "bolder");
		sessionsTable.setItems(musicDAO.getAllListeningSessions());
//		sessionsTable.setHeightByRows(15);
		
		sessionsTable.asSingleSelect().addValueChangeListener(event -> openUpdateListeningSessionWindow(event.getValue()));
		
		sessionsTable.setColumns("bandName", "albumName", "portion", "comment", "concept", "sessionDate");
		sessionsTable.getColumnByKey("bandName").setHeader("Band");
		sessionsTable.getColumnByKey("albumName").setHeader("Album");
		sessionsTable.getColumnByKey("portion").setHeader("Portion");
		sessionsTable.getColumnByKey("comment").setHeader("Comment");
		sessionsTable.getColumnByKey("concept").setHeader("Concept?");
		sessionsTable.getColumnByKey("sessionDate").setHeader("Date");
		
		sessionsLayout.add(sessionsTable);
	}


	private void setMusicPopupWindow() {
		listeningSessionWindow = new Dialog();
		listeningSessionWindow.setHeightFull();
		listeningSessionWindow.setWidth("480px");
		listeningSessionWindow.setHeight("600px");
		listeningSessionWindowLayout = new FormLayout();
		listeningSessionWindowLayout.setHeightFull();
		listeningSessionWindowLayout.setWidthFull();
		listeningSessionWindowLayout.setClassName("musicWindowBackground");
		listeningSessionWindow.add(listeningSessionWindowLayout);
		listeningSessionWindow.setCloseOnOutsideClick(true);
	}

	
	private void openCreateListeningSessionWindow() {
		setMusicPopupWindow();

		bandNameAutocompleteField = new Autocomplete(5);
//		bandNameAutocompleteField.getElement().setAttribute("colspan", "2");
		bandNameAutocompleteField.getElement().setAttribute("background-color", "white");
		bandNameAutocompleteField.getElement().setAttribute("opacity", "0.9");
		bandNameAutocompleteField.getElement().setAttribute("font-weight", "bolder");
		bandNameAutocompleteField.getElement().setAttribute("border-radius", "3px");
		bandNameAutocompleteField.getElement().setAttribute("padding", "0");
		bandNameAutocompleteField.setOptions(musicDAO.getAllBands());
		bandNameAutocompleteField.addChangeListener(event -> {
		    bandNameAutocompleteField.setOptions(musicDAO.getAllBandsForAutocomplete(event.getValue()));
		});
		    
		albumNameAutocompleteField = new Autocomplete (5);
//		albumNameAutocompleteField.getElement().setAttribute("colspan", "2");
		albumNameAutocompleteField.getElement().setAttribute("background-color", "white");
		albumNameAutocompleteField.getElement().setAttribute("opacity", "0.9");
		albumNameAutocompleteField.getElement().setAttribute("font-weight", "bolder");
		albumNameAutocompleteField.getElement().setAttribute("border-radius", "3px");
		albumNameAutocompleteField.getElement().setAttribute("padding", "0");
		albumNameAutocompleteField.setOptions(musicDAO.getAllAlbumsForBand(bandNameAutocompleteField.getValue()));
		albumNameAutocompleteField.addChangeListener(event -> {
			albumNameAutocompleteField.setOptions(musicDAO.getAllAlbumsForAutocomplete(bandNameAutocompleteField.getValue(), event.getValue()));
		});
		
		commentTextField = new TextField();
//		commentTextField.getElement().setAttribute("colspan", "2");
		commentTextField.setClassName("klmTextField");
		commentTextField.setWidth("90%");
		
		portionComboBox = new ComboBox<String>();
		portionComboBox.setClassName("klmTextField");
		portionComboBox.setItems(Arrays.asList(new String[] { "1", "1/2", "1/3", "2/3", "1/4", "2/4", "3/4" }));
		portionComboBox.setValue("1");
		
		dateField = new DatePicker();
		dateField.setClassName("klmTextField");
		dateField.setValue(LocalDate.now());
		
		conceptCheckBox = new Checkbox();
		conceptCheckBox.setValue(false);
		conceptCheckBox.setClassName("klmTextField");
		
//		HorizontalLayout dateAndPortionLayout = new HorizontalLayout();
//		dateAndPortionLayout.add(dateField);
//		dateAndPortionLayout.add(portionComboBox);
		
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.add(new Button("Submit", event -> {
			musicDAO.addListeningSession(bandNameAutocompleteField.getValue(), albumNameAutocompleteField.getValue(), portionComboBox.getValue().toString(), commentTextField.getValue(), dateField.getValue(), conceptCheckBox.getValue());
			listeningSessionWindow.close();}));
		buttonsLayout.add(new Button("Close", event -> listeningSessionWindow.close()));
		
		listeningSessionWindowLayout.addFormItem(bandNameAutocompleteField, "Band");
		listeningSessionWindowLayout.addFormItem(albumNameAutocompleteField, "Album");
		listeningSessionWindowLayout.addFormItem(commentTextField, "Comment");
		listeningSessionWindowLayout.addFormItem(dateField, "Date");
		listeningSessionWindowLayout.addFormItem(portionComboBox, "Portion");
		listeningSessionWindowLayout.addFormItem(conceptCheckBox, "Concept?");
		listeningSessionWindowLayout.add(buttonsLayout);
		listeningSessionWindowLayout.setResponsiveSteps(new ResponsiveStep("0", 1));
		listeningSessionWindow.open();
	}
	
	private void openUpdateListeningSessionWindow(MusicBean listeningSession) {
		if (listeningSession == null) {
			return;
		}
		setMusicPopupWindow();

		bandNameTextField = new TextField();
//		bandNameTextField.getElement().setAttribute("colspan", "2");
		bandNameTextField.setWidth("90%");
		bandNameTextField.setClassName("klmTextField");
		
		albumNameTextField = new TextField();
//		albumNameTextField.getElement().setAttribute("colspan", "2");
		albumNameTextField.setWidth("90%");
		albumNameTextField.setClassName("klmTextField");
		
		commentTextField = new TextField();
//		commentTextField.getElement().setAttribute("colspan", "2");
		commentTextField.setWidth("90%");
		commentTextField.setClassName("klmTextField");
		
		portionComboBox = new ComboBox<String>();
		portionComboBox.setClassName("klmTextField");
		portionComboBox.setItems(Arrays.asList(new String[] { "1", "1/2", "1/3", "2/3", "1/4", "2/4", "3/4" }));
		dateField = new DatePicker();
		dateField.setClassName("klmTextField");
		conceptCheckBox = new Checkbox();
		conceptCheckBox.setClassName("klmTextField");
		
//		HorizontalLayout dateAndPortionLayout = new HorizontalLayout();
//		dateAndPortionLayout.add(dateField);
//		dateAndPortionLayout.add(portionComboBox);
		
		bandNameTextField.setValue(listeningSession.getBandName() != null ? listeningSession.getBandName() : "");
		albumNameTextField.setValue(listeningSession.getAlbumName() != null ? listeningSession.getAlbumName() : "");
		portionComboBox.setValue(listeningSession.getPortion() != null ? listeningSession.getPortion() : "1");
		commentTextField.setValue(listeningSession.getComment() != null ? listeningSession.getComment() : "");
		dateField.setValue(listeningSession.getSessionDate() != null ? listeningSession.getSessionDate() : LocalDate.now());
		conceptCheckBox.setValue(listeningSession.getConcept() != null ? listeningSession.getConcept() : true);
		
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.add(new Button("Submit", event -> {
			musicDAO.editListeningSession(bandNameTextField.getValue(), albumNameTextField.getValue(), portionComboBox.getValue().toString(), commentTextField.getValue(), dateField.getValue(), conceptCheckBox.getValue(), listeningSession.getId());
			listeningSessionWindow.close();}));
		buttonsLayout.add(new Button("Close", event -> listeningSessionWindow.close()));

		listeningSessionWindowLayout.addFormItem(bandNameTextField, "Band");
		listeningSessionWindowLayout.addFormItem(albumNameTextField, "Album");
		listeningSessionWindowLayout.addFormItem(commentTextField, "Comment");
		listeningSessionWindowLayout.addFormItem(dateField, "Date");
		listeningSessionWindowLayout.addFormItem(portionComboBox, "Portion");
		listeningSessionWindowLayout.addFormItem(conceptCheckBox, "Concept?");
		listeningSessionWindowLayout.add(buttonsLayout);
		listeningSessionWindowLayout.setResponsiveSteps(new ResponsiveStep("0", 1));
		listeningSessionWindow.open();
	}
}
