package top.haidong556.chat_server.common.response;

public class SuccessResponse<E> extends BaseResponse<E>{
    public SuccessResponse(String msg) {
        super(200, msg);
    }
    public SuccessResponse(String msg,E data){
        super(200,msg,data);
    }
}
