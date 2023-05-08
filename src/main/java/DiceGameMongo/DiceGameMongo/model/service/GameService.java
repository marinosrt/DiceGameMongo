package DiceGameMongo.DiceGameMongo.model.service;

import DiceGameMongo.DiceGameMongo.model.dto.GameDTO;
import DiceGameMongo.DiceGameMongo.model.dto.PlayerDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    GameDTO playGame(ObjectId id);

    List<String> getAllPlayers();

    List<GameDTO> getGamesPlayerId(ObjectId id);

    String getAverageAllPlayers();

    PlayerDTO getBestLooser();

    PlayerDTO getBestWinner();

    PlayerDTO updatePlayerId(PlayerDTO playerDTO, ObjectId id);

    int deleteGamesId(ObjectId id);

    boolean playerExistById(ObjectId id);

}
