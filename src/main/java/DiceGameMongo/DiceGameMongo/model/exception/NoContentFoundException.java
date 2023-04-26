package DiceGameMongo.DiceGameMongo.model.exception;

public class NoContentFoundException extends RuntimeException{

    private String message;

    public NoContentFoundException(String message) {
        super(message);
    }

}
