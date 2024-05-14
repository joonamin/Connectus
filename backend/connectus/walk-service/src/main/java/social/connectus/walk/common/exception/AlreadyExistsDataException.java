package social.connectus.walk.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.ALREADY_REPORTED)
public class AlreadyExistsDataException extends RuntimeException {
    public AlreadyExistsDataException(String msg){
        super(msg);
    }
    public AlreadyExistsDataException(String msg, Throwable cause){
        super(msg);
    }
}
