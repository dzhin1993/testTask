package zhynkoilya.tourist_bot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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

    public SendMessage createSendMessage(Message message) {
        String inputMessage = message.getText();
        String replyMessage;

        if (inputMessage.equals("/start")) {
            replyMessage = getResponse("reply.askCity");
        } else {
            City city = repository.getByName(inputMessage);
            replyMessage = city != null ? city.getMessage() : getResponse("reply.notFoundCity");
        }

        return new SendMessage(message.getChatId(), replyMessage);
    }


    private String getResponse(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }
}
