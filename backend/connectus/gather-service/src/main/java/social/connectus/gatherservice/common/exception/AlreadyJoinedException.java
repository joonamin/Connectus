package social.connectus.gatherservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.ALREADY_REPORTED)
public class AlreadyJoinedException extends Exception {
    public AlreadyJoinedException(String msg){
        super(msg);
    }
    public AlreadyJoinedException(String msg, Throwable cause){
        super(msg);
    }
}
