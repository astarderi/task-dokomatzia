package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    //מתודה מחייבת של בוט - כל מה שקורה בבוט שלנו. מאזינה לבוט שלנו ואוספת את כל המידע שקרה בבוט שלנו ובזכותה אפשר לבצע פעולות נוספות
    @Override
    public void onUpdateReceived(Update update) {

        // שליחת הודעה בחזרה למישהו ששלח לי הודעה
        String message = null;
        if ((update.getMessage() != null && update.getMessage().getFrom().getFirstName().length() > 0))
            message = "hello " + update.getMessage().getFrom().getFirstName();
        else {
            message = "Hello stranger! ";
        }
        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), message);
        //שליחת ההודעה עצמה
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


        //הדפסת ההודעה עצמה שנשלחת בבוט
        System.out.println("Someone sent message" + update.getMessage().getText());
        //הדפסת מי שלח את ההודעה והדפסת ההודעה עצמה
        if (update.getMessage() != null && update.getMessage().getFrom().getFirstName().length() > 0) {
            System.out.println("new message from: " + update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName());
        } else {
            System.out.println("Someone sdent message: ");
        }
        System.out.println(update.getMessage().getText());

    }


    //מתודה מחייבת של בוט - העתקת היוזרניים שבחרנו לבוט
    @Override
    public String getBotUsername() {

        return "astar04Bot  ";
    }

    //מתודה מחייבת של בוט - העתקת הטוקן שהעתקנו
    public String getBotToken() {
        return "6646053351:AAFkAJgUTK9KvFMzeQkGL3gDGddpK73S5Jw";
    }
}