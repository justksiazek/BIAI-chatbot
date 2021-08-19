package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("HomeView")
@Route(value = "HomeView", layout = MainLayout.class)
public class HomeView extends Div {
    public HomeView() {
        addClassName("home-view");

        H1 mainHeader = new H1("Welcome to Chatbot Centre!");
        add(mainHeader);
        H3 mainDescription1 = new H3("On this page you can chat with your chosen Chatbot.");
        H3 mainDescription2 = new H3("Alice is a nice community-written bot. " +
                "She is pretty polished and very advanced and responsive. " +
                "She is a very good example of what we can achieve by using AIML.");
        H3 mainDescription3 = new H3("Chatty is a baby chatbot - " +
                "it's written by authors of this program and is not as polished as Alice. " +
                "Nonetheless it's very much loved by it's authors and it's implementation shows the basics of AIML.");
        add(mainDescription1, mainDescription2, mainDescription3);

        VaadinSession.getCurrent().setAttribute("freshLoad", "loaded");

    }
}
