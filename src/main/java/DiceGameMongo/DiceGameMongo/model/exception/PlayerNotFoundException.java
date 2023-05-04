package DiceGameMongo.DiceGameMongo.model.exception;

import org.bson.types.ObjectId;

public class PlayerNotFoundException extends RuntimeException{

    private String resourceName;

    private ObjectId idNumber;

    public PlayerNotFoundException(String resourceName, ObjectId idNumber) {
        super(String.format("'%s not found with ID: '%s", resourceName, idNumber));
        this.resourceName = resourceName;
        this.idNumber = idNumber;
    }

    public PlayerNotFoundException(String resourceName) {
        super(resourceName);
    }

}
