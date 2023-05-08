package DiceGameMongo.DiceGameMongo.model.exception;

import lombok.RequiredArgsConstructor;

public class NotAuthorisedPlayerException extends RuntimeException{

    private String message;

    public NotAuthorisedPlayerException(String message) {
        super(message);
    }
}
