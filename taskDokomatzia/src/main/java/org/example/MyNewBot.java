package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.net.URI;

public class MyNewBot extends TelegramLongPollingBot {

    private static final String BASE_URL = "https://app.seker.live/fm1/";

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyNewBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "astar04Bot";
    }

    @Override
    public String getBotToken() {
        return "6646053351:AAFkAJgUTK9KvFMzeQkGL3gDGddpK73S5Jw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText();
            CloseableHttpClient client = HttpClients.createDefault();
            URI uri = new URIBuilder("https://app.seker.live/fm1/send-message")
                    .setParameter("id", "206819658")
                    .setParameter("text", message)
                    .build();
            HttpGet request = new HttpGet(uri);
            CloseableHttpResponse response = client.execute(request);
            //System.out.println(EntityUtils.toString(response.getEntity()));

            String chatResponse = EntityUtils.toString(response.getEntity());
            System.out.println(chatResponse);
            Response response1 = new ObjectMapper().readValue(chatResponse, Response.class);
            SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), response1.getExtra());
            System.out.println(response1.getExtra());
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}