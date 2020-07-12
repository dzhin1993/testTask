package zhynkoilya.tourist_bot.telegram;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import zhynkoilya.tourist_bot.telegram.TouristTelegramBot;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public TouristTelegramBot telegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        TouristTelegramBot touristTelegramBot = new TouristTelegramBot(options);
        touristTelegramBot.setBotUserName(botUserName);
        touristTelegramBot.setBotToken(botToken);
        touristTelegramBot.setWebHookPath(webHookPath);

        return touristTelegramBot;
    }
}
