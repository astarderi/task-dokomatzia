package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText();
            CloseableHttpClient client = HttpClients.createDefault();
            URI uri = new URIBuilder("https://app.seker.live/fm1/send-message")
                    .setParameter("id", "")
                    .setParameter("text", message)
                    .build();
            HttpGet request = new HttpGet(uri);
            CloseableHttpResponse response = client.execute(request);
            String chatResponse = EntityUtils.toString(response.getEntity());
            ChatResponse response1 = new ObjectMapper().readValue(chatResponse, ChatResponse.class);
            SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), response1.getExtra());
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken () {
        return "7292079966:AAGZFbx2AdZ9-Q5u6oJHdQEnYiMKBLnKhrI";
    }

    @Override
    public String getBotUsername() {
        return "Shai0404Bot";
    }
}
