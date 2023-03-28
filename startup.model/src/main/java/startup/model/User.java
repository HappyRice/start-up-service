package startup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User extends BaseModel {

    @Column
    private String name;

    @Column
    private String email;

    public User() {
    }

    public User(final Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public static class Builder {
        private String name;
        private String email;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}