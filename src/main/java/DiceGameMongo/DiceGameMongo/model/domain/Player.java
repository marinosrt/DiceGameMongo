package DiceGameMongo.DiceGameMongo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Players")
public class Player {

    @Id
    private ObjectId id;

    private String name;
    private LocalDateTime registration;

    private List<Game> gameList = new ArrayList<>();

    public void addGame(Game game) {
        gameList.add(game);
    }

    public void deleteGames(){
        gameList.clear();
    }

    public List<String> printGames(){

        return gameList.stream()
                .map(Game::toString)
                .collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", registration = " + registration +
                ", gameList = " + printGames() +
                '}';
    }
}
