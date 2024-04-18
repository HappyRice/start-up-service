package startup.model;

import org.hibernate.annotations.Filter;
import startup.common.enumeration.GameState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Game")
@NamedQueries({
        @NamedQuery(name = "getGameByGuid", query = "SELECT game FROM Game as game where guid = :guid"),
        @NamedQuery(name = "getGameByCode", query = "SELECT game FROM Game as game where code = :code")
})
public class Game extends BaseModel {

    @Column(nullable = false)
    private String code;

    @Column
    private LocalDateTime activeDate;

    @Column
    @Enumerated(value = EnumType.STRING)
    private GameState state;

    @OneToOne
    @JoinColumn(name = "winnerId")
    private Player winner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    @Filter(name = "notDeleted")
    private List<Player> players = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "game")
    private GameSetting setting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currentHandId")
    private Hand currentHand;

    public Game() {
    }

    public Game(final Builder builder) {
        this.code = builder.code;
        this.activeDate = builder.activeDate;
        this.state = builder.state;
        this.winner = builder.winner;
        this.players = builder.players != null ? builder.players : new ArrayList<>();
        this.setting = builder.setting;
        this.currentHand = builder.currentHand;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public LocalDateTime getActiveDate() {
        return this.activeDate;
    }

    public void setActiveDate(final LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public GameState getState() {
        return this.state;
    }

    public void setState(final GameState state) {
        this.state = state;
    }

    public Player getWinner() {
        return this.winner;
    }

    public void setWinner(final Player winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public GameSetting getSetting() {
        return this.setting;
    }

    public Hand getCurrentHand() {
        return this.currentHand;
    }

    public void setCurrentHand(final Hand currentHand) {
        this.currentHand = currentHand;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private LocalDateTime activeDate;
        private GameState state;
        private Player winner;
        private List<Player> players;
        private GameSetting setting;
        private Hand currentHand;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withCode(final String code) {
            this.code = code;
            return this;
        }

        public Builder withActiveDate(final LocalDateTime activeDate) {
            this.activeDate = activeDate;
            return this;
        }

        public Builder withState(final GameState state) {
            this.state = state;
            return this;
        }

        public Builder withWinner(final Player winner) {
            this.winner = winner;
            return this;
        }

        public Builder withPlayers(final List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder withSetting(final GameSetting setting) {
            this.setting = setting;
            return this;
        }

        public Builder withCurrentHand(final Hand currentHand) {
            this.currentHand = currentHand;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

}