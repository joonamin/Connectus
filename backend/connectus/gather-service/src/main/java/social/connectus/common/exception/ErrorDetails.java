package social.connectus.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String msg;
    private String details;
}
