package top.haidong556.deal.common.StatusModelComponent;

public class BaseStatusModel<S extends BaseStatus,E extends BaseEvent>{
    protected S currentStatus;
    protected S lastStatus;

    public boolean transformStatusByEvent(E event){
        S nextStatus= (S) currentStatus.getTargetStatus(event);
        if(nextStatus!=null){
            lastStatus=currentStatus;
            currentStatus=nextStatus;
            return true;
        }
        else{
            throw new RuntimeException();
        }
    }
}
