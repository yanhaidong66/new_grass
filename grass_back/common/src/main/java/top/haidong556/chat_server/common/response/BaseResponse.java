package top.haidong556.chat_server.common.response;

import lombok.Data;

@Data
public class BaseResponse<E> {
    private int code;
    private String msg;
    private E data;
    public BaseResponse(int code,String msg){
        this.code=code;
        this.msg=msg;
        this.data=null;
    }

    public BaseResponse(int code, String msg, E data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
