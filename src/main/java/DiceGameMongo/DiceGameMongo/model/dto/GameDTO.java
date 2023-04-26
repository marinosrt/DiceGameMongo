package DiceGameMongo.DiceGameMongo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private String id;

    private int dice1;

    private int dice2;

    private String status;

}
