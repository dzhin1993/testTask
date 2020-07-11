package zhynkoilya.tourist_bot.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorInfo {
    private final String url;
    private final String detail;
    private final HttpStatus status;
}