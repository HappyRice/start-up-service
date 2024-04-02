package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.Game;

@Repository
public class PlayerRepository extends BaseRepository {

    public Game getPlayerById(final int id) {
        return this.getById(Game.class, id);
    }

    public Game getPlayerByGuid(final String guid) {
        final Query<?> query = this.getNamedQuery("getPlayerByGuid");
        query.setParameter("guid", guid);

        return (Game) this.getUniqueResult(query);
    }
}