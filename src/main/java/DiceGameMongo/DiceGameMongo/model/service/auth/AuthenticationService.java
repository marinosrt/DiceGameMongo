package DiceGameMongo.DiceGameMongo.model.service.auth;

import DiceGameMongo.DiceGameMongo.controller.auth.AuthenticationRequest;
import DiceGameMongo.DiceGameMongo.controller.auth.AuthenticationResponse;
import DiceGameMongo.DiceGameMongo.controller.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authentication(AuthenticationRequest request);

}
