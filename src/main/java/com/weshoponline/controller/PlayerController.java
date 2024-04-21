package com.weshoponline.controller;

import com.weshoponline.model.Player;
import com.weshoponline.model.Team;
import com.weshoponline.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAllPlayers() {
        return playerService.findAllPlayers();
    }

    @QueryMapping
    public Optional<Player> findPlayerById(@Argument Integer id) {
        return playerService.findPlayerById(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team){
        return playerService.createPlayer(name, team);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team){
        return playerService.updatePlayer(id, name, team);
    }

    @MutationMapping
    public Player delete(@Argument Integer id){
        return playerService.deletePlayer(id);
    }
}
