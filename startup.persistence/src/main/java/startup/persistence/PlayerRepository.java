package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.Player;

import java.util.Optional;

@Repository
public class PlayerRepository extends BaseRepository {

    public Optional<Player> getPlayerById(final int id) {
        return Optional.ofNullable(this.getById(Player.class, id));
    }

    public Optional<Player> getPlayerByGuid(final String guid) {
        final Query<?> query = this.getNamedQuery("getPlayerByGuid");
        query.setParameter("guid", guid);

        return Optional.ofNullable((Player) this.getUniqueResult(query));
    }
}