package ma.enset.cqrsaxon.commonApi.events;

import lombok.Getter;
import ma.enset.cqrsaxon.commonApi.enums.AccountState;

public class AccountActivatedEvent extends baseEvent<String> {
    @Getter private AccountState state;
    public AccountActivatedEvent(String id,AccountState accountState) {
        super(id);
        this.state=accountState;
    }
}
