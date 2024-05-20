package top.haidong556.deal.common.StatusModelComponent;

import java.util.Objects;

public class StatusEventPair<S extends BaseStatus,E extends BaseEvent> {


    private final S status;
    private final E event;

    public StatusEventPair(S s,E e){
        this.status=s;
        this.event=e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEventPair<?, ?> that = (StatusEventPair<?, ?>) o;
        return Objects.equals(status, that.status) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, event);
    }
}
