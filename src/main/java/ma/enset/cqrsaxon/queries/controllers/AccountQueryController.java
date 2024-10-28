package ma.enset.cqrsaxon.queries.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.cqrsaxon.commonApi.queries.GetAccountByIdQuery;
import ma.enset.cqrsaxon.commonApi.queries.GetAllAccountsQuery;
import ma.enset.cqrsaxon.queries.entities.Account;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController("/queries/accounts")
@Slf4j
public class AccountQueryController {

    private QueryGateway queryGateway;

    @GetMapping("/allAccounts")
    public List<Account> allAccounts(){
        List<Account> response=queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
        return response;
    }
    @GetMapping("/ById/{id}")
    public Account accountById(@PathVariable String id){
        return queryGateway.query(new GetAccountByIdQuery(id),ResponseTypes.instanceOf(Account.class)).join();
    }

}
