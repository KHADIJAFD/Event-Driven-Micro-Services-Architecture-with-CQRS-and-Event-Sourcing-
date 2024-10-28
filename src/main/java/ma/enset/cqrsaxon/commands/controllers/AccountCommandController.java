package ma.enset.cqrsaxon.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.cqrsaxon.commonApi.commands.CreateAccountCmd;
import ma.enset.cqrsaxon.commonApi.commands.CreditAccountCmd;
import ma.enset.cqrsaxon.commonApi.commands.DebitAccountCmd;
import ma.enset.cqrsaxon.commonApi.dtos.CreateAccountRequestDTO;
import ma.enset.cqrsaxon.commonApi.dtos.CreditAccountRequestDTO;
import ma.enset.cqrsaxon.commonApi.dtos.DebitAccountRequestDTO;
import ma.enset.cqrsaxon.commonApi.exceptions.InvalidAmountException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/commands/accounts")
@AllArgsConstructor
public class AccountCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping ("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) throws ExecutionException, InterruptedException {

        CompletableFuture<String> commandResponse=commandGateway.send(new CreateAccountCmd(
                UUID.randomUUID().toString(),
                request.getCurrency(),
                request.getInitialBalance()
        ));
        return commandResponse;
    }
    @PutMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request){
        CompletableFuture<String> commandResponse=commandGateway.send(new CreditAccountCmd(
                request.getAccountId(),
                request.getCurrency(),
                request.getInitialBalance()
        ));
        return commandResponse;
    }
    @PutMapping("/Debit")
    public CompletableFuture<String> creditAccount(@RequestBody DebitAccountRequestDTO request) {
        CompletableFuture<String> commandResponse=commandGateway.send(new DebitAccountCmd(
                request.getAccountId(),
                request.getCurrency(),
                request.getInitialBalance()
        ));
        return commandResponse;
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity =new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return entity;
    }

    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }


}
