package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.Hand;

@Repository
public class HandRepository extends BaseRepository {

    public Hand getHandById(final int id) {
        return this.getById(Hand.class, id);
    }

    public Hand getHandByGuid(final String guid) {
        final Query<?> query = this.getNamedQuery("getHandByGuid");
        query.setParameter("guid", guid);

        return (Hand) this.getUniqueResult(query);
    }
}