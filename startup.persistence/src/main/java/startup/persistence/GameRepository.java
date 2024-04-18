package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.Game;

import java.util.Optional;

@Repository
public class GameRepository extends BaseRepository {

    public Optional<Game> getGameById(final int id) {
        return Optional.ofNullable(this.getById(Game.class, id));
    }

    public Optional<Game> getGameByGuid(final String guid) {
        final Query<?> query = this.getNamedQuery("getGameByGuid");
        query.setParameter("guid", guid);

        return Optional.ofNullable((Game) this.getUniqueResult(query));
    }

    public Optional<Game> getGameByCode(final String code) {
        final Query<?> query = this.getNamedQuery("getGameByCode");
        query.setParameter("code", code);

        return Optional.ofNullable((Game) this.getUniqueResult(query));
    }
}