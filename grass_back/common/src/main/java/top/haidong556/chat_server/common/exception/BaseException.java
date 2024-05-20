package top.haidong556.chat_server.common.exception;

public class BaseException extends RuntimeException{
    public final static int MYBATIS_ERROR_CODE=810;

    protected ExceptionType exceptionType;
    public BaseException(ExceptionType exceptionType){
        this.exceptionType=exceptionType;
    }
    public BaseException(int code,String msg) {
        this.exceptionType=new CustomExceptionType(code,msg);
    }
    public ExceptionType getExceptionType() {
        return exceptionType;
    }
    public int getCode(){return exceptionType.getCode();}
    public String getMsg(){return exceptionType.getMsg();}

    @Override
    public String toString() {
        return "BaseException{" +
                "exceptionCode=" + getCode() +
                "exceptionMsg="+getMsg()+
                '}';
    }
    public String toJson() {
        return "{\"exceptionCode\":" + getCode() + ",\"exceptionMsg\":\"" + getMsg() + "\"}";
    }
}
