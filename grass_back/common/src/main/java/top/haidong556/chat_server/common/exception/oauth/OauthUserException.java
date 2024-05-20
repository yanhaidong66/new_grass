package top.haidong556.chat_server.common.exception.oauth;

import top.haidong556.chat_server.common.exception.BaseException;
import top.haidong556.chat_server.common.exception.ExceptionType;

public class OauthUserException extends BaseException {
    public OauthUserException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public OauthUserException(int code, String msg) {
        super(code, msg);
    }

    public int getExceptionCode(){
        return  exceptionType.getCode();
    }
    public String getMsg(){
        return exceptionType.getMsg();
    }
    public enum OauthUserExceptionType implements ExceptionType{
        USER_HAVE_EXIST(1100,"add user have exist")
        ,USER_NOT_EXIST(1101,"user not exist")
        ,USER_PASSWORD_UPDATE_FAIL(1102,"user password update wrong")
        ,USER_ADD_WRONG(1103,"add user have wrong")
        ,USER_DELETE_WRONG(1104,"delete user wrong");
        int code;
        String msg;
        OauthUserExceptionType(int code,String msg){
            this.code=code;
            this.msg=msg;
        }

        @Override
        public int getCode() {
            return code;
        }
        @Override
        public String getMsg(){
            return msg;
        }
    }
}
