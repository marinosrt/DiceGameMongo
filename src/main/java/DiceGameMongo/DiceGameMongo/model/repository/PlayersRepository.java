package DiceGameMongo.DiceGameMongo.model.repository;

import DiceGameMongo.DiceGameMongo.model.domain.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayersRepository extends MongoRepository<Player, ObjectId> {

    Optional<Player> findByEmail(String email);

    //Optional<Player> existByName(String name);
    //boolean existByName(String name);

}
