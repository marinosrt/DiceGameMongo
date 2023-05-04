package DiceGameMongo.DiceGameMongo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private ObjectId id;

    private String name;

    @CreationTimestamp
    private LocalDateTime registration = LocalDateTime.now();

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
