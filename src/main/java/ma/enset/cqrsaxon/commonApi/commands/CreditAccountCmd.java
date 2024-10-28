package ma.enset.cqrsaxon.commonApi.commands;

import lombok.Getter;

public class CreditAccountCmd extends BaseCommand<String>{
    @Getter private String currency;
    @Getter private double amount;
    public CreditAccountCmd(String id,String currency,double amount) {
        super(id);
        this.amount=amount;
        this.currency=currency;
    }
}
