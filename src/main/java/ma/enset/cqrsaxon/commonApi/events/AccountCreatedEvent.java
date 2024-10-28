package ma.enset.cqrsaxon.commonApi.events;

import lombok.Getter;
import ma.enset.cqrsaxon.commonApi.enums.AccountState;

public class AccountCreatedEvent extends baseEvent<String> {

    @Getter private double amount;
    @Getter private String currency;
    @Getter private AccountState status;

    public AccountCreatedEvent(String id, double amount, String currency, AccountState status) {
        super(id);
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }


}
