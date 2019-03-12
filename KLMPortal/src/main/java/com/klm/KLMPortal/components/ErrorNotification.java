package com.klm.KLMPortal.components;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;

public class ErrorNotification extends Notification {
	private static final long serialVersionUID = 1L;
	private static final String BUTTON_CAPTION = "Close";
	
	public ErrorNotification(String _errorMessage) {
		getElement().getStyle().set("background-color", "red");
        Label content = new Label(_errorMessage);
        NativeButton buttonInside = new NativeButton(BUTTON_CAPTION);
        buttonInside.getStyle().set("background-color", "black");
        add(content, buttonInside);
        setDuration(10000);
        buttonInside.addClickListener(event -> close());
        setPosition(Position.MIDDLE);
        // end-source-example
	}
	
	public static ErrorNotification show(String _errorMessage) {
		ErrorNotification notification = new ErrorNotification(_errorMessage);
		notification.open();
		return notification;

	}
}
