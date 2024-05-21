package social.connectus.gatherservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class InvalidHostIdException extends RuntimeException {
    public InvalidHostIdException(String msg){
        super(msg);
    }
    public InvalidHostIdException(String msg, Throwable cause){
        super(msg);
    }
}
