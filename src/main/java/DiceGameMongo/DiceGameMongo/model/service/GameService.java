package DiceGameMongo.DiceGameMongo.model.service;

import DiceGameMongo.DiceGameMongo.model.dto.GameDTO;
import DiceGameMongo.DiceGameMongo.model.dto.PlayerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    GameDTO playGame(String id);

    List<String> getAllPlayers();

    List<GameDTO> getGamesPlayerId(String id);

    String getAverageAllPlayers();

    PlayerDTO getBestLooser();

    PlayerDTO getBestWinner();

    PlayerDTO updatePlayerId(PlayerDTO playerDTO, String id);

    int deleteGamesId(String id);

}
