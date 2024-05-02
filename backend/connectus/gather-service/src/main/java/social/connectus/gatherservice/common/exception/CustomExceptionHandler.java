package social.connectus.gatherservice.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({GlobalException.class})
    public ResponseEntity globalExceptionHandler(GlobalException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClosedGatherException.class)
    public ResponseEntity closedGatherException(ClosedGatherException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidHostIdException.class)
    public ResponseEntity invalidHostIdException(InvalidHostIdException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(errorDetails, HttpStatus.FORBIDDEN);
    }


}
