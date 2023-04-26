package DiceGameMongo.DiceGameMongo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Game {

    @Id
    private String id;

    private int dice1 = (int) (Math.random() * 6 + 1);

    private int dice2 = (int) (Math.random() * 6 + 1);

    private String status;

    private Player player;

    public Game(Player player) {
        this.player = player;
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

}
