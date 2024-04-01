package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.Game;

@Repository
public class GameRepository extends BaseRepository {

    public Game getGameById(final int id) {
        return this.getById(Game.class, id);
    }

    public Game getGameByGuid(final String guid) {
        final Query<?> query = this.getNamedQuery("getGameByGuid");
        query.setParameter("guid", guid);

        return (Game) this.getUniqueResult(query);
    }
}