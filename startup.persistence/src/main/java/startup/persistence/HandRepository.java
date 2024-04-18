package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.Hand;

import java.util.Optional;

@Repository
public class HandRepository extends BaseRepository {

    public Optional<Hand> getHandById(final int id) {
        return Optional.ofNullable(this.getById(Hand.class, id));
    }

    public Optional<Hand> getHandByGuid(final String guid) {
        final Query<?> query = this.getNamedQuery("getHandByGuid");
        query.setParameter("guid", guid);

        return Optional.ofNullable((Hand) this.getUniqueResult(query));
    }
}