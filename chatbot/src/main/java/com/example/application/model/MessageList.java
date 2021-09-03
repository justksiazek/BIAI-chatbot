package com.example.application.model;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import org.vaadin.artur.Avataaar;

public class MessageList extends Div {

    private Div filler = new Div();

    public MessageList() {
        setClassName(getClass().getSimpleName());
        filler.addClassName(getClass().getSimpleName() + "-gap");
        add(filler);
    }

    public void addMessage(String sender, Avataaar avatar, String msg, boolean isUser) {
        Span fromContainer = new Span(new Text(sender));
        fromContainer.addClassName(getClass().getSimpleName() + "-name");

        Div textContainer = new Div(new Text(msg));
        textContainer.addClassName(getClass().getSimpleName() + "-bubble");

        Div avatarContainer = new Div(avatar, fromContainer);
        avatarContainer.addClassName(getClass().getSimpleName() + "-avatar");

        Div line = new Div(avatarContainer, textContainer);
        line.addClassName(getClass().getSimpleName() + "-row");

        add(line);

        if (isUser) {
            line.addClassName(getClass().getSimpleName() + "-row-currentUser");
            textContainer.addClassName(getClass().getSimpleName() + "-bubble-currentUser");
        }
        line.getElement().callJsFunction("scrollIntoView");

        remove(filler);
        add(filler);
        filler.getElement().callJsFunction("scrollIntoView");
    }

    public void clear() {
        removeAll();
    }
}
