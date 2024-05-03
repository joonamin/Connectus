package social.connectus.gatherservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.ALREADY_REPORTED)
public class ClosedGatherException extends RuntimeException {
    public ClosedGatherException(String msg){
        super(msg);
    }
    public ClosedGatherException(String msg, Throwable cause){
        super(msg);
    }
}
