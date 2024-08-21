package com.jarierca.gamevault.repository;

import com.jarierca.gamevault.entity.Player;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<Player> {

    /**
     * Finds a player by their username.
     *
     * @param username the username of the player.
     * @return the player with the specified username, or null if not found.
     */
    public Player findByUsername(String username) {
        return find("username", username).firstResult();
    }

    /**
     * Finds a player by their email.
     *
     * @param email the email of the player.
     * @return the player with the specified email, or null if not found.
     */
    public Player findByEmail(String email) {
        return find("email", email).firstResult();
    }

    /**
     * Saves a new player or updates an existing one.
     *
     * @param player the player to be saved or updated.
     * @return the saved or updated player.
     */
    @Transactional
    public Player save(Player player) {
        if (player.getId() == null) {
            persist(player); // Insert new player
        } else {
            // If the ID exists, update the existing player
            player = getEntityManager().merge(player);
        }
        return player;
    }
}
