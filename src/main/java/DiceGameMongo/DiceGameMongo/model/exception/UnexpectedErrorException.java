package DiceGameMongo.DiceGameMongo.model.exception;

public class UnexpectedErrorException extends RuntimeException {

    private String message;

    public UnexpectedErrorException(String message) {
        super(message);
    }



}
