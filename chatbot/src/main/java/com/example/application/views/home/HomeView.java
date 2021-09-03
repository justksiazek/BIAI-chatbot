package com.example.application.views.home;

import com.example.application.views.MainLayout;
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
        MainLayout.unselectTabs();
        MainLayout.homeTab.removeClassName(MainLayout.homeTab.getClassName());
        MainLayout.homeTab.setClassName("selected");

        addClassName("home-view");

        H1 mainHeader = new H1("Welcome to Chatbot Centre!");
        add(mainHeader);
        H3 mainDescription1 = new H3("On this page you can chat with your chosen Chatbot. ");
        H3 mainDescription2 = new H3("Alice is a nice community-written bot. " +
                "It's pretty polished and very advanced and responsive. " +
                "You can hold a full conversation with it and have some fun with it " +
                "- there isn't much that it can't talk about." +
                "It's is a very good example of what we can achieve by using AIML.");
        H3 mainDescription3 = new H3("Clown is a community-written bot that likes unfunny jokes and games. " +
                "It's a somewhat weird bot with whom you cant just talk normally - " +
                "to check him out you can ask for a horoscope reading, a joke or a game of battledome. " +
                "It's an example of what else can be archived by using AIML.");
        H3 mainDescription4 = new H3("Chatty is a baby chatbot - " +
                "it's written by authors of this program and is not as polished as Alice. " +
                "It can hold a basic conversation, but cannot recall a lot of information and just sometimes " +
                "it becomes really confused, It's not really the talkative type, either." +
                "Nonetheless it's very much loved by it's authors and it's implementation shows the basics of AIML.");
        add(mainDescription1, mainDescription2, mainDescription3, mainDescription4);

        VaadinSession.getCurrent().setAttribute("freshLoad", "loaded");

    }
}
