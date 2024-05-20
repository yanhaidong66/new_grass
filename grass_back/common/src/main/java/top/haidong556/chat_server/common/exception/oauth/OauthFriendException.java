package top.haidong556.chat_server.common.exception.oauth;

import top.haidong556.chat_server.common.exception.BaseException;
import top.haidong556.chat_server.common.exception.ExceptionType;

public class OauthFriendException extends BaseException {
    public OauthFriendException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public OauthFriendException(int code, String msg) {
        super(code, msg);
    }

    public int getExceptionCode() {
        return exceptionType.getCode();
    }

    public String getMsg() {
        return exceptionType.getMsg();
    }

    public enum OauthFriendExceptionType implements ExceptionType {
        FRIEND_HAVE_EXIST(1200, "add friend have exist"),
        FRIEND_ADD_USER_NOT_EXIST(1201, "add friend user not exist"),
        FRIEND_ADD_FAIL(1202, "friend add fail"),
        FRIEND_DELETE_FAIL(1203,"friend delete fail"),
        FRIEND_NOT_EXIST(1204,"friend not exist in db"),
        FRIEND_GET_FAIL(1205,"friend list get fail");

        int code;
        String msg;

        OauthFriendExceptionType(int code, String msg) {
            this.code = code;
            this.msg = msg;
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