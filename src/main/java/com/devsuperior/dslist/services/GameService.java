package com.devsuperior.dslist.services;


import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){

        List<Game> listGames = gameRepository.findAll();

        //Pra quem nao entendeu esse trecho de codigo esta retornando o list games mapeado,
        // ou seja transformando o objeto Game em GameMinDTO.
        return listGames.stream().
                map(GameMinDTO::new) //expresão lambda que transforma o objeto usando o construtor de GameMinDTO recebendo um Game
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long id) {
        return gameRepository.findById(id)
                .map(game -> ResponseEntity.ok(new GameDTO(game)))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
}
