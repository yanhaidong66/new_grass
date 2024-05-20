package top.haidong556.chat_server.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.haidong556.chat_server.common.response.ExceptionResponse;
@Slf4j
@ControllerAdvice
public class AllExceptionHandler {
    //private final Logger logger;

//    public AllExceptionHandler(Logger logger) {
//        this.logger = logger;
//    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ExceptionResponse handleSomeException(BaseException e){
        //logger
        log.error(e.getMsg());
        return new ExceptionResponse(e);
    }
}
