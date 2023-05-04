package DiceGameMongo.DiceGameMongo.model.service;

import DiceGameMongo.DiceGameMongo.model.domain.Game;
import DiceGameMongo.DiceGameMongo.model.domain.Player;
import DiceGameMongo.DiceGameMongo.model.dto.GameDTO;
import DiceGameMongo.DiceGameMongo.model.dto.PlayerDTO;
import DiceGameMongo.DiceGameMongo.model.exception.UnexpectedErrorException;
import DiceGameMongo.DiceGameMongo.model.repository.PlayersRepository;
import DiceGameMongo.DiceGameMongo.model.service.utils.GameUtils;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService, GameUtils {

    @Autowired
    private ModelMapper modelMapper;

    private PlayersRepository playersRepository;

    public GameServiceImpl(PlayersRepository playersRepository) {
        super();
        this.playersRepository = playersRepository;
    }

    //region SERVICE CONTROLLER

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player playerRequest;
        PlayerDTO playerDtoResponse;

        try {
            playerRequest = playerConvertEntity(playerDTO);
            playerRequest.setName(setPlayersName(playerRequest));

            if (playerRequest.getName().equalsIgnoreCase("")) {
                return null;
            } else {
                playersRepository.save(playerRequest);
                playerDtoResponse = playerConvertDTO(playerRequest);
                playerDtoResponse.setSuccessRate(calculateRate(playerDtoResponse));

                return playerDtoResponse;
            }
        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Unexpected error!");
        }

    }

    @Override
    public GameDTO playGame(ObjectId id) {
        Player playerRequest;
        Game game;

        try {
            playerRequest = getOptionalPlayer(id);
            game = new Game();
            playerRequest.addGame(game);
            playersRepository.save(playerRequest);

            return gameConvertDTO(game);

        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Unexpected error!");
        }
    }

    @Override
    public List<String> getAllPlayers() {
        List<String> returnList;

        try {
            if (playersRepository.findAll().isEmpty()) {
                returnList = null;
            } else {
                returnList = new ArrayList<>();
                for (Player player : playersRepository.findAll().stream().toList()) {
                    returnList.add(player.toString() + ". Winning Rate: " + calculateRate(playerConvertDTO(player)));
                }
            }

            return returnList;

        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Unexpected error!");
        }
    }

    @Override
    public List<GameDTO> getGamesPlayerId(ObjectId id) {
        Player playerRequest;
        List<GameDTO> gamesList = null;

        try {
            playerRequest = getOptionalPlayer(id);

            if (!playerRequest.getGameList().isEmpty()) {
                gamesList = playerRequest.getGameList().stream().map(this::gameConvertDTO).collect(Collectors.toList());
            }

            return gamesList;

        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Unexpected error!");
        }
    }

    @Override
    public String getAverageAllPlayers() {
        String output = "";
        List<Player> playerList;
        List<Game> gameList = new ArrayList<>();

        try {
            if (!playersRepository.findAll().isEmpty()) {
                playerList = playersRepository.findAll().stream().toList();

                if (playerList.stream().anyMatch(player -> !player.getGameList().isEmpty())) {
                    for (Player player : playerList) {
                        if (!player.getGameList().isEmpty()) {
                            gameList.addAll(player.getGameList());
                        }
                    }

                    output = getAverageRate(gameList);
                } else {
                    output = "none";
                }
            }

            return output;

        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Unexpected error!");
        }
    }

    @Override
    public PlayerDTO getBestLooser() {
        List<PlayerDTO> playerDTOList;

        playerDTOList = getSuccessRatePlayers();
        playerDTOList.removeIf(playerDTO -> playerDTO.getSuccessRate().equalsIgnoreCase("Still haven't played any game."));

        return playerDTOList.stream()
                .min(Comparator.comparingDouble(player -> Double.parseDouble(player.getSuccessRate())))
                .stream().findAny()
                .orElse(null);
    }

    @Override
    public PlayerDTO getBestWinner() {
        List<PlayerDTO> playerDTOList;

        playerDTOList = getSuccessRatePlayers();
        playerDTOList.removeIf(playerDTO -> playerDTO.getSuccessRate().equalsIgnoreCase("Still haven't played any game."));

        return playerDTOList.stream()
                .max(Comparator.comparingDouble(player -> Double.parseDouble(player.getSuccessRate())))
                .stream().findAny()
                .orElse(null);
    }

    @Override
    public PlayerDTO updatePlayerId(PlayerDTO playerDTO, ObjectId id) {
        Player playerRequest;
        PlayerDTO playerDtoResponse;

        try {
            playerRequest = getOptionalPlayer(id);
            playerRequest.setName(playerDTO.getName());
            playersRepository.save(playerRequest);

            playerDtoResponse = playerConvertDTO(playerRequest);
            playerDtoResponse.setSuccessRate(calculateRate(playerDtoResponse));

            return playerDtoResponse;

        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Unexpected error!");
        }
    }

    @Override
    public int deleteGamesId(ObjectId id) {
        int found = 0;
        Player playerRequest;

        try {
            if (playerExistById(id)) {
                playerRequest = getOptionalPlayer(id);

                if (!playerRequest.getGameList().isEmpty()) {
                    playerRequest.deleteGames();
                    playersRepository.save(playerRequest);

                    found = 2;
                } else {
                    found = 1;
                }
            }

            return found;

        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Unexpected error!");
        }
    }

    @Override
    public boolean playerExistById(ObjectId id) {

        return playersRepository.existsById(id);

    }

    //endregion SERVICE


    //region UTILS

    @Override
    public Player getOptionalPlayer(ObjectId id) {
        Optional<Player> playerData = playersRepository.findById(id);
        return playerData.get();
    }

    @Override
    public PlayerDTO playerConvertDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }

    @Override
    public Player playerConvertEntity(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    @Override
    public GameDTO gameConvertDTO(Game game) {
        return modelMapper.map(game, GameDTO.class);
    }

    @Override
    public List<PlayerDTO> getSuccessRatePlayers() {
        List<PlayerDTO> playerDTOList;

        try {
            playerDTOList = playersRepository.findAll().stream()
                    .map(this::playerConvertDTO)
                    .collect(Collectors.toList());

            playerDTOList.forEach(player -> player.setSuccessRate(calculateRate(player)));

            return playerDTOList;

        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Something went wrong while trying to update the player.");
        }
    }

    @Override
    public String calculateRate(PlayerDTO playerDTO) {
        String output = "";
        Player player;

        try {
            if (playerExistById(playerDTO.getId())) {
                player = getOptionalPlayer(playerDTO.getId());

                if (!player.getGameList().isEmpty()) {
                    output = getAverageRate(player.getGameList());
                } else {
                    output = "Still haven't played any game.";
                }
            }

            return output;
        } catch (UnexpectedErrorException e) {
            throw new UnexpectedErrorException("Something went wrong while trying to update the player.");
        }
    }

    public String getAverageRate(List<Game> gameList) {
        double result = gameList.stream()
                .mapToDouble(game -> game.getStatus().equals("WINNER") ? 1 : 0)
                .average()
                .orElse(0.0) * 100.0;

        return String.format("%.2f%%", result);
    }

    @Override
    public String setPlayersName(Player player) {String playerName;

        if (player.getName().equalsIgnoreCase("") || player.getName().isEmpty()){
            playerName = "ANONYMOUS";
        } else {
            if(!checkString(player.getName())){ // check if it already exists into the DDBB
                playerName = player.getName();
            } else {
                playerName = "";
            }
        }

        return playerName;
    }

    @Override
    public boolean checkString(String name) {
        return playersRepository.findAll().stream().anyMatch(player -> player.getName().equalsIgnoreCase(name));
    }

    //endregion UTILS
}
