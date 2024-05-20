package top.haidong556.chat_server.common.exception.oauth;

import top.haidong556.chat_server.common.exception.BaseException;
import top.haidong556.chat_server.common.exception.ExceptionType;

public class OauthJwtException extends BaseException {
    public OauthJwtException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public OauthJwtException(int code, String msg) {
        super(code, msg);
    }

    public enum CreateJwtExceptionType implements ExceptionType{
        REQUEST_USERNAME_EMAIL_BOTH_NULL(1000,"request userName and email both null")
        ,USER_NOT_FOUND(1001,"request user not found in user_db")
        ,USER_PASSWORD_WRONG(1002,"request user password is wrong");

        int code;
        String msg;
        CreateJwtExceptionType(int code, String msg){
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
