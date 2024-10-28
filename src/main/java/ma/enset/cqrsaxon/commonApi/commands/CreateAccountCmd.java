package ma.enset.cqrsaxon.commonApi.commands;

import lombok.Getter;

public class CreateAccountCmd extends BaseCommand<String> {

    @Getter private String currency;
    @Getter private double amount;

    public CreateAccountCmd(String id,String currency,double balance) {
        super(id);
        this.amount=balance;
        this.currency=currency;

    }
}
