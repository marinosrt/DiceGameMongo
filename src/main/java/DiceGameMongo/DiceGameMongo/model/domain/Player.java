package DiceGameMongo.DiceGameMongo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Players")
public class Player implements UserDetails {

    @Id
    private ObjectId id;

    private Role role;

    private String name;

    private String email;

    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
