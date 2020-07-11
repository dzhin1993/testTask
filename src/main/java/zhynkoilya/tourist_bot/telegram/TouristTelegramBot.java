package zhynkoilya.tourist_bot.telegram;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import zhynkoilya.tourist_bot.model.City;
import zhynkoilya.tourist_bot.repository.CitiesRepository;

@Setter
public class TouristTelegramBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Autowired
    CitiesRepository repository;

    public TouristTelegramBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long id = update.getMessage().getChatId();
            try {
                execute(createSendMessage(id, update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    private SendMessage createSendMessage(long id, Update update) {
        City city = repository.getByCity(update.getMessage().getText());
        String reply = city != null ? city.getMessage() : "Not found this city";
        return new SendMessage(id, reply);
    }
}
