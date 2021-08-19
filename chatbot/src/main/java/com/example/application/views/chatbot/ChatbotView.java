package com.example.application.views.chatbot;

import com.example.application.model.MessageList;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.example.application.views.MainLayout;
import com.vaadin.flow.shared.communication.PushMode;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.vaadin.artur.Avataaar;

@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Chatbot")
public class ChatbotView extends Div implements HasUrlParameter<String> {

    private final UI ui;
    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private Chat chatSession;
    private Bot bot;

    public ChatbotView() {
        ui = UI.getCurrent();

        message.setPlaceholder("What do you want to say?");
        message.setSizeFull();

        Button send = new Button(VaadinIcon.PAPERPLANE.create(), event -> sendMessage());
        send.addClickShortcut(Key.ENTER);

        HorizontalLayout inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");

        add(messageList, inputLayout);
        setSizeFull();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {ui.getPushConfiguration().setPushMode(PushMode.AUTOMATIC);}

    private void sendMessage() {
        String text = message.getValue();

        if (!text.trim().isEmpty()) {
            messageList.addMessage("You", new Avataaar("patient"), text, true);
            message.clear();

            String answer = chatSession.multisentenceRespond(text);
            ui.access(() -> messageList.addMessage(bot.getName(), new Avataaar("chatboy"), answer, false));
        }
    }

    @Override
    public void setParameter(BeforeEvent event, String botName) {
        if(botName.equals("Alice")) {
            bot = new Bot(BotConfiguration.builder()
                    .name("alice")
                    .path("src/main/resources")
                    .build());
        }
        else {
            bot = new Bot(BotConfiguration.builder()
                    .name("chatty")
                    .path("src/main/resources")
                    .build());
        }
        chatSession = new Chat(bot);
        messageList.clear();
    }

    public String getBotName() {
        return bot.getName();
    }
}
