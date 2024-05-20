package top.haidong556.chat_server.common.exception;

public class CustomExceptionType implements ExceptionType{
    private int code;
    private String msg;
    public CustomExceptionType(int code, String msg){
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
