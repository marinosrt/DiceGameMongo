package DiceGameMongo.DiceGameMongo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Game {

    @Id
    private ObjectId id;

    private final int dice1 = (int) (Math.random() * 6 + 1);

    private final int dice2 = (int) (Math.random() * 6 + 1);

    private String status;

    public Game() {
        this.status = calculate();
    }

    private String calculate() {
        String result;

        if((this.dice1 + this.dice2) == 7){
            result = "WINNER";
        } else {
            result = "LOOSER";
        }

        return result;
    }

    @Override
    public String toString() {
        return "- GAME {" +
                "Dice1 = " + dice1 +
                ". Dice2 = " + dice2 +
                ". Status = '" + status + "'" +
                '}';
    }
}
