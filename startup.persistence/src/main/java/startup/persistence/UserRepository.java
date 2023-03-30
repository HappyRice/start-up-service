package startup.persistence;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import startup.model.User;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository {

    public User getUserById(final int id) {
        return this.getById(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        final Query<?> query = this.getNamedQuery("getAllUsers");

        return (List<User>) this.list(query);
    }


}