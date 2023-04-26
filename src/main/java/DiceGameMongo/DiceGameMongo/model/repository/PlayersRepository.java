package DiceGameMongo.DiceGameMongo.model.repository;

import DiceGameMongo.DiceGameMongo.model.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends MongoRepository<Player, String> {

}
