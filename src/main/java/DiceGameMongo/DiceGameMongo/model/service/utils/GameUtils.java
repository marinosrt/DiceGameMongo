package DiceGameMongo.DiceGameMongo.model.service.utils;

import DiceGameMongo.DiceGameMongo.model.domain.Game;
import DiceGameMongo.DiceGameMongo.model.domain.Player;
import DiceGameMongo.DiceGameMongo.model.dto.GameDTO;
import DiceGameMongo.DiceGameMongo.model.dto.PlayerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameUtils {

    PlayerDTO playerConvertDTO(Player player);

    Player playerConvertEntity(PlayerDTO playerDTO);

    GameDTO gameConvertDTO(Game game);

    List<PlayerDTO> getSuccessRatePlayers();

    String calculateRate(PlayerDTO playerDTO);

    String setPlayersName(Player player);

    boolean checkString(String name);

}
