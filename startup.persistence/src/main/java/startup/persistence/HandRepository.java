package startup.persistence;

import org.springframework.stereotype.Repository;
import startup.model.Game;

@Repository
public class HandRepository extends BaseRepository {

    public Game getHandById(final int id) {
        return this.getById(Game.class, id);
    }
}