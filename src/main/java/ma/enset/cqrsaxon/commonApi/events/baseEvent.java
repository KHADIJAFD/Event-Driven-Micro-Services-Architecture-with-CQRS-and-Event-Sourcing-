package ma.enset.cqrsaxon.commonApi.events;

import lombok.Getter;

public class baseEvent<T> {
   @Getter
   private T id;

    public baseEvent(T id) {
        this.id = id;
    }
}
