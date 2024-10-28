package ma.enset.cqrsaxon.commonApi.exceptions;

public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(String s) {
        super(s);
    }
}
