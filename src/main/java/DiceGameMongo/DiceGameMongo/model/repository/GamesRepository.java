package DiceGameMongo.DiceGameMongo.model.repository;

import DiceGameMongo.DiceGameMongo.model.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends MongoRepository<Game, String> {

}
