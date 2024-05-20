package top.haidong556.chat_server.common.exception;

public class GeneralException extends BaseException{
    public GeneralException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public GeneralException(int code, String msg) {
        super(code, msg);
    }


    public int getExceptionCode(){
        return  exceptionType.getCode();
    }
    public String getMsg(){
        return exceptionType.getMsg();
    }
    public enum GeneralExceptionType implements ExceptionType{
        FAIL(800,"fail"),
        MYBATIS_EXCEPTION(801,"mybatis exception");
        int code;
        String msg;
        GeneralExceptionType(int code,String msg){
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
