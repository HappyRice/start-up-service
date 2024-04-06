package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.Player;

@Repository
public class PlayerRepository extends BaseRepository {

    public Player getPlayerById(final int id) {
        return this.getById(Player.class, id);
    }

    public Player getPlayerByGuid(final String guid) {
        final Query<?> query = this.getNamedQuery("getPlayerByGuid");
        query.setParameter("guid", guid);

        return (Player) this.getUniqueResult(query);
    }
}