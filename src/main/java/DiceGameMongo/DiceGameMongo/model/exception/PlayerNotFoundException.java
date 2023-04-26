package DiceGameMongo.DiceGameMongo.model.exception;

public class PlayerNotFoundException extends RuntimeException{

    private String resourceName;

    private String idNumber;

    public PlayerNotFoundException(String resourceName, String idNumber) {
        super(String.format("'%s not found with ID: '%s", resourceName, idNumber));
        this.resourceName = resourceName;
        this.idNumber = idNumber;
    }

}
