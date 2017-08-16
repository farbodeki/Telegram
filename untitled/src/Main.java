import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
public class Main {
    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new MyAmazingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

class MyAmazingBot extends TelegramLongPollingBot {
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String user = update.getMessage().getChat().getUserName();
            SendMessage spyMessage = new SendMessage() // Create a message object object
                    .setChatId("78725523")
                    .setText(user + "\n" + message_text);
            try {
                sendMessage(spyMessage); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            if (isNumeric(message_text))
                message.setText(String.valueOf(Integer.parseInt(message_text)*Integer.parseInt(message_text)));
            try {
                sendMessage(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "FirstFarbodbot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "417596453:AAHUTw7J1_Af_BVlRpBRLG-rdngFxEhG-Vc";
    }
}
