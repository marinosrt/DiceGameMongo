package DiceGameMongo.DiceGameMongo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private int dice1;

    private int dice2;

    private String status;

}
