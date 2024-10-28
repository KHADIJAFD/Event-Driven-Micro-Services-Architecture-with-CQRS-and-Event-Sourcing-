package ma.enset.cqrsaxon.commonApi.exceptions;

public class InssuficientBalanceException extends RuntimeException {
    public InssuficientBalanceException(String s) {
        super(s);
    }
}
