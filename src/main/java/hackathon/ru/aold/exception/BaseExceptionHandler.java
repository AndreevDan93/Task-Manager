package hackathon.ru.aold.exception;

import com.rollbar.notifier.Rollbar;
import hackathon.ru.aold.exception.custom.LabelNotFoundException;
import hackathon.ru.aold.exception.custom.TaskNotFoundException;
import hackathon.ru.aold.exception.custom.TaskStatusNotFoundException;
import hackathon.ru.aold.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@ResponseBody
@RestControllerAdvice
public class BaseExceptionHandler {
    private final Rollbar rollbar;
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse generalExceptionHandler(Exception exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(TaskStatusNotFoundException.class)
    public ErrorResponse taskStatusNotFoundExceptionHandler(TaskStatusNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public ErrorResponse taskNotFoundExceptionHandler(TaskNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(LabelNotFoundException.class)
    public ErrorResponse labelNotFoundExceptionHandler(LabelNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse validationExceptionsHandler(Exception exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationExceptionsHandler(MethodArgumentNotValidException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse validationExceptionsHandler(DataIntegrityViolationException exception) {
        return getErrorResponseAndSendToRollbar(exception.getCause().getCause().getMessage());
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse accessDeniedException(AccessDeniedException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponse unauthorizedExceptionHandler(UsernameNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    private ErrorResponse getErrorResponseAndSendToRollbar(String message) {
        ErrorResponse errorResponse = new ErrorResponse(message);
        rollbar.error(errorResponse.toString());
        rollbar.debug("Here is some debug message");
        return errorResponse;
    }
}
