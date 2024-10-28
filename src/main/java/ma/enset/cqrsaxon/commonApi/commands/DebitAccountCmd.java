package ma.enset.cqrsaxon.commonApi.commands;

import lombok.Getter;

public class DebitAccountCmd extends BaseCommand<String>{

    @Getter private String currency;
    @Getter private double amount;

    public DebitAccountCmd(String id,String currency,double amount) {
            super(id);
            this.amount=amount;
            this.currency=currency;

    }
}
