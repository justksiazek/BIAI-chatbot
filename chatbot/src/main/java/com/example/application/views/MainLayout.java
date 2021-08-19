package com.example.application.views;

import com.example.application.views.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.router.Route;
import com.example.application.views.chatbot.ChatbotView;
import com.vaadin.flow.theme.lumo.Lumo;
import org.vaadin.artur.Avataaar;

/**
 * The main view is a top-level placeholder for other views.
 */
@CssImport("./themes/chatbot/styles.css")
@PWA(name = "chatbot", shortName = "chatbot", enableInstallPrompt = false)
@Theme(value = Lumo.class)
@Route(value="")
public class MainLayout extends AppLayout {

    private Tabs menu = new Tabs();
    private Tab aliceTab;
    private Tab chattyTab;
    private String destination = "home";

    public MainLayout() {
        if(VaadinSession.getCurrent().getAttribute("freshLoad") == null) {
            UI.getCurrent().navigate(HomeView.class);
            UI.getCurrent().getPage().reload();
            return;
        }

        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setClassName("sidemenu-header");
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        Icon icon = new Icon(VaadinIcon.CHAT);
        icon.setSize("50px");
        icon.setColor("white");
        layout.add(icon);

        H1 viewTitle = new H1("Chatbot");
        layout.add(viewTitle);

        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setClassName("sidemenu-menu");
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new H1("Chatbot"));

        layout.add(logoLayout, menu);

        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        final Tab tab = new Tab();
        tab.add(new Avataaar("Home Page"));
        tab.add(new RouterLink("Home Page", HomeView.class));
        ComponentUtil.setData(tab, Class.class, HomeView.class);
        tabs.add(tab);
        tabs.add(createTab("Alice"));
        tabs.add(createTab("Chatty"));
        return tabs;
    }

    private Tab createTab(String botName) {
        final Tab tab = new Tab();
        tab.add(new Avataaar(botName));
        tab.add(new RouterLink(botName, ChatbotView.class, botName));
        ComponentUtil.setData(tab, Class.class, ChatbotView.class);
        if(botName.equals("Alice")) {
            aliceTab = tab;
        }
        else {
            chattyTab = tab;
        }
        return tab;
    }

    @Override
    protected void afterNavigation() {
        if(destination.equals("home")) {
            return;
        }
        else {
            destination = "bot";
            super.afterNavigation();
            ChatbotView view = (ChatbotView) getContent();
            String botName = view.getBotName();
            if(botName.equals("Alice")) {
                menu.setSelectedTab(aliceTab);
            }
            else {
                menu.setSelectedTab(chattyTab);
            }
        }
    }
}
