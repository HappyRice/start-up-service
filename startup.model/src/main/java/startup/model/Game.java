package startup.model;

import org.hibernate.annotations.Where;
import startup.common.enumeration.GameType;

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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GameType type;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private Integer winsRequired;

    @OneToMany(mappedBy = "game")
    @Where(clause = "deletedDate IS NULL")
    private final List<Player> players = new ArrayList<>();

    public Game() {
    }

    public Game(final Builder builder) {
        this.code = builder.code;
        this.type = builder.type;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.winsRequired = builder.winsRequired;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public GameType getType() {
        return this.type;
    }

    public void setType(final GameType type) {
        this.type = type;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getWinsRequired() {
        return this.winsRequired;
    }

    public void setWinsRequired(final Integer winsRequired) {
        this.winsRequired = winsRequired;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private GameType type;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer winsRequired;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withCode(final String code) {
            this.code = code;
            return this;
        }

        public Builder withType(final GameType type) {
            this.type = type;
            return this;
        }

        public Builder withStartDate(final LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(final LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withWinsRequired(final Integer winsRequired) {
            this.winsRequired = winsRequired;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

}