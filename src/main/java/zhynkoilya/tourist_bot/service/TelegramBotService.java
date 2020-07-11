package zhynkoilya.tourist_bot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import zhynkoilya.tourist_bot.model.City;
import zhynkoilya.tourist_bot.repository.CitiesRepository;

import java.util.Locale;

@Service
public class TelegramBotService {

    private final CitiesRepository repository;
    private final MessageSource messageSource;

    public TelegramBotService(CitiesRepository repository, MessageSource messageSource) {
        this.repository = repository;
        this.messageSource = messageSource;
    }

    public SendMessage createSendMessage(long id, Update update) {
        String inputMessage = update.getMessage().getText();
        String replyMessage;

        if (inputMessage.equals("/start")) {
            replyMessage = getMessage("reply.askCity");
        } else {
            City city = repository.getByCity(inputMessage);
            replyMessage = city != null ? city.getMessage() : getMessage("reply.notFoundCity");
        }

        return new SendMessage(id, replyMessage);
    }


    private String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }
}
