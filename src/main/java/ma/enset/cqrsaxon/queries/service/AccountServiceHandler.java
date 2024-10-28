package ma.enset.cqrsaxon.queries.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.cqrsaxon.commonApi.enums.OperationType;
import ma.enset.cqrsaxon.commonApi.events.AccountActivatedEvent;
import ma.enset.cqrsaxon.commonApi.events.AccountCreatedEvent;
import ma.enset.cqrsaxon.commonApi.events.AccountCreditedEvent;
import ma.enset.cqrsaxon.commonApi.events.AccountDebitedEvent;
import ma.enset.cqrsaxon.commonApi.queries.GetAccountByIdQuery;
import ma.enset.cqrsaxon.commonApi.queries.GetAllAccountsQuery;
import ma.enset.cqrsaxon.queries.entities.Account;
import ma.enset.cqrsaxon.queries.entities.Operation;
import ma.enset.cqrsaxon.queries.repositories.AccountRepository;
import ma.enset.cqrsaxon.queries.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("********************************************");
        log.info("accountCreatedEvent: received!");
        Account account=new Account();
        account.setId(event.getId());
        account.setAmount(event.getAmount());
        account.setCurrency(event.getCurrency());
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("********************************************");
        log.info("accountActivatedEvent received!");
        Account account=accountRepository.findById(event.getId()).get();
        account.setStatus(event.getState());
        accountRepository.save(account);

    }
    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("********************************************");
        log.info("accountDebitedEvent received!");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setOperationType(OperationType.DEBIT);
        operation.setAmount(event.getBalance());
        operation.setCreatedAt(event.getDate());
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setAmount(account.getAmount()-event.getBalance());
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("********************************************");
        log.info("accountDebitedEvent received!");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setOperationType(OperationType.DEBIT);
        operation.setAmount(event.getBalance());
        operation.setCreatedAt(event.getDate());
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setAmount(account.getAmount()+event.getBalance());
        accountRepository.save(account);
    }
    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return accountRepository.findById(query.getId()).get();
    }
}
