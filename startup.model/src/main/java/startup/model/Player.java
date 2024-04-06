package startup.model;

import org.hibernate.annotations.FilterJoinTable;

import javax.persistence.*;

@Entity
@Table(name = "Player")
@NamedQueries({
        @NamedQuery(name = "getPlayerByGuid", query = "SELECT player FROM Player as player where guid = :guid")
})
public class Player extends BaseModel {

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gameId")
    @FilterJoinTable(name = "notDeleted", condition = "deletedDate IS NULL")
    private Game game;

    @Column
    private int winCounter;

    public Player() {
    }

    public Player(final Builder builder) {
        this.name = builder.name;
        this.game = builder.game;
        this.winCounter = builder.winCounter;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(final Game game) {
        this.game = game;
    }

    public int getWinCounter() {
        return this.winCounter;
    }

    public void setWinsCounter(final int winCounter) {
        this.winCounter = winCounter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Game game;
        private int winCounter;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withGame(final Game game) {
            this.game = game;
            return this;
        }

        public Builder withWinCounter(final int winCounter) {
            this.winCounter = winCounter;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

}