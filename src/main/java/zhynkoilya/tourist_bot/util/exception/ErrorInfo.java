package zhynkoilya.tourist_bot.util.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorInfo {
    private final String url;
    private final String detail;
}