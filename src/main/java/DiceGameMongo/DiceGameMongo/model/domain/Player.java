package DiceGameMongo.DiceGameMongo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Players")
public class Player {

    @Id
    private String id;

    private String name;

    @CreatedDate
    private LocalDateTime registration;

    private List<Game> gameList = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        //this.registration = LocalDateTime.now();
    }

    public void addGame(Game game) {
        gameList.add(game);
    }

}
