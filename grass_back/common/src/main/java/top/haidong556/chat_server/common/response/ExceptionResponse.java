package top.haidong556.chat_server.common.response;

import top.haidong556.chat_server.common.exception.BaseException;

public class ExceptionResponse<E extends BaseException> extends BaseResponse<E>{

    public ExceptionResponse(E e) {
        super(e.getCode(), e.getMsg());
    }

}
