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
    public static final Tab aliceTab = new Tab();
    public static final Tab chattyTab = new Tab();
    public static final Tab homeTab = new Tab();
    public static final Tab clownTab = new Tab();

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

        Icon icon = new Icon(VaadinIcon.CHAT);
        icon.setClassName("logo");

        layout.add(icon, menu);

        return layout;
    }

    private Tabs createMenu() {
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);

        homeTab.setClassName("selected");
        homeTab.add(new Avataaar("MyHoMe"));
        homeTab.add(new RouterLink("Home Page", HomeView.class));
        ComponentUtil.setData(homeTab, Class.class, HomeView.class);
        tabs.add(homeTab);

        aliceTab.setClassName("unselected");
        aliceTab.add(new Avataaar("F C"));
        aliceTab.add(new RouterLink("Alice", ChatbotView.class, "Alice"));
        ComponentUtil.setData(aliceTab, Class.class, ChatbotView.class);
        tabs.add(aliceTab);

        clownTab.setClassName("unselected");
        clownTab.add(new Avataaar("clowClown"));
        clownTab.add(new RouterLink("Clown", ChatbotView.class, "Clown"));
        ComponentUtil.setData(clownTab, Class.class, ChatbotView.class);
        tabs.add(clownTab);

        chattyTab.setClassName("unselected");
        chattyTab.add(new Avataaar("P H"));
        chattyTab.add(new RouterLink("Chatty", ChatbotView.class, "Chatty"));
        ComponentUtil.setData(chattyTab, Class.class, ChatbotView.class);
        tabs.add(chattyTab);

        return tabs;
    }

    public static void unselectTabs() {
        homeTab.removeClassName(homeTab.getClassName());
        homeTab.setClassName("unselected");
        aliceTab.removeClassName(aliceTab.getClassName());
        aliceTab.setClassName("unselected");
        chattyTab.removeClassName(chattyTab.getClassName());
        chattyTab.setClassName("unselected");
        clownTab.removeClassName(clownTab.getClassName());
        clownTab.setClassName("unselected");
    }
}
