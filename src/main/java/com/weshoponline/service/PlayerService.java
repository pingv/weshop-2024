package com.weshoponline.service;

import com.weshoponline.model.Player;
import com.weshoponline.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();
    AtomicInteger id =  new AtomicInteger(0);

    public List<Player> findAllPlayers() {
        return players;
    }

    public Optional<Player> findPlayerById(Integer playerID) {
        return players.stream()
                .filter(player -> player.playerID().equals(playerID)).findFirst();
    }

    public Player createPlayer(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player deletePlayer(int playerID) {
        Player player = players.stream().filter(player1 -> player1.playerID() == playerID)
                .findFirst().orElseThrow(IllegalArgumentException::new);
        players.remove(player);
        return player;
    }

    public Player updatePlayer(int id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> aPlayer = players.stream().filter(player -> player.playerID() == id).findFirst();

        if(aPlayer.isPresent()) {
            Player player = aPlayer.get();
            int index = players.indexOf(player);
            players.set(index, updatedPlayer);
        } else {
            throw new IllegalArgumentException("Player not found");
        }
        return updatedPlayer; //???
    }

    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Ravindra Jadeja", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Rohit", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Jasiwal", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Krishna", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Kishan", Team.GT));
        players.add(new Player(id.incrementAndGet(), "Suryakumar Yadav", Team.GT));

    }
}
