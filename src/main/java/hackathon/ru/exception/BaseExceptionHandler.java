package hackathon.ru.exception;

import com.rollbar.notifier.Rollbar;
import hackathon.ru.exception.custom.*;
import hackathon.ru.model.VacancyStatus;
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

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

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
    @ExceptionHandler(ApplicationNotFoundException.class)
    public ErrorResponse applicationNotFoundExceptionHandler(ApplicationNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ApplicationStatusNotFoundException.class)
    public ErrorResponse applicationStatusNotFoundExceptionHandler(ApplicationStatusNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(CandidateNotFoundException.class)
    public ErrorResponse candidateNotFoundExceptionHandler(CandidateNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    public ErrorResponse cityStatusNotFoundExceptionHandler(CityNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ErrorResponse roleNotFoundExceptionHandler(RoleNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(VacancyNotFoundException.class)
    public ErrorResponse vacancyNotFoundExceptionHandler(VacancyNotFoundException exception) {
        return getErrorResponseAndSendToRollbar(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(VacancyStatusNotFoundException.class)
    public ErrorResponse vacancyStatusNotFoundExceptionHandler(VacancyStatusNotFoundException exception) {
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
