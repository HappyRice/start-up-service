package startup.model;

import javax.persistence.*;

@Entity
@Table(name = "Player")
@NamedQueries({
        @NamedQuery(name = "getPlayerByGuid", query = "SELECT game FROM Game as game where guid = :guid")
})
public class Player extends BaseModel {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    private Game game;

    @Column
    private Integer winCounter;

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

    public Integer getWinCounter() {
        return this.winCounter;
    }

    public void setWinsCounter(final Integer winCounter) {
        this.winCounter = winCounter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Game game;
        private Integer winCounter;

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

        public Builder withWinCounter(final Integer winCounter) {
            this.winCounter = winCounter;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

}