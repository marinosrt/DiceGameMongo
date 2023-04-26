package DiceGameMongo.DiceGameMongo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private String id;

    private String name;

    private LocalDateTime registration;

    private String successRate;

    @Override
    public String toString() {
        return "Player {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", successRate = " + successRate + " %" +
                '}';
    }

}
