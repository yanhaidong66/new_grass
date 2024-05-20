package top.haidong556.chat_server.common.exception.chat.gateway;

import top.haidong556.chat_server.common.exception.BaseException;
import top.haidong556.chat_server.common.exception.ExceptionType;

public class TokenAuthenticationException extends BaseException {
    public TokenAuthenticationException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public TokenAuthenticationException(int code, String msg) {
        super(code, msg);
    }

    public enum TokenAuthenticationExceptionType implements ExceptionType{
        TOKEN_AUTHORIZATION_NULL(2000,"http header authorization field is null")
        ,TOKEN_AUTHORIZATION_TYPE_NOT_BEARER(2001,"http header authorization field type is not bearer")
        ,TOKEN_AUTHORIZATION_FORMAT_WRONG(2002,"http header authorization field token is null");

        int code;
        String msg;
        TokenAuthenticationExceptionType(int code, String msg){
            this.code=code;
            this.msg=msg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }
}
