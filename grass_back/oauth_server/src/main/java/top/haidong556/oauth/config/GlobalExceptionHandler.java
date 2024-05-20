package top.haidong556.oauth.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;
import top.haidong556.chat_server.common.exception.BaseException;
import top.haidong556.chat_server.common.exception.oauth.OauthFriendException;
import top.haidong556.chat_server.common.exception.oauth.OauthUserException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({OauthUserException.class, OauthFriendException.class})
    public ResponseEntity<String> handleGeneralException(HttpServletRequest request, BaseException ex) {
        String methodName =  request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE).toString();
        String uuid = (String) request.getAttribute("UUID");
        log.warn("RequestUUID:{},throw exception in {},exception msg:{}", uuid, methodName,ex.getMsg());
        if(ex.getCode()==BaseException.MYBATIS_ERROR_CODE)
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("db option wrong");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.toJson());
    }
}
