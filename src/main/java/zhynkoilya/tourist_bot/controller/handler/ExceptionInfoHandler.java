package zhynkoilya.tourist_bot.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zhynkoilya.tourist_bot.controller.TouristBotRestController;
import zhynkoilya.tourist_bot.util.exception.ErrorInfo;
import zhynkoilya.tourist_bot.util.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice(basePackageClasses = TouristBotRestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 6)
public class ExceptionInfoHandler {

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e, UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(req, e, CONFLICT);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorInfo illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, INTERNAL_SERVER_ERROR);
    }

    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, HttpStatus status) {
        Throwable rootCause = getRootCause(e);
        String url = req.getRequestURL().toString();
        log.error("error at request " + url, rootCause);
        return new ErrorInfo(url, rootCause.getMessage(), status);
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}