package startup.model;

import org.hibernate.annotations.FilterJoinTable;
import startup.common.enumeration.GameType;

import javax.persistence.*;

@Entity
@Table(name = "GameSetting")
public class GameSetting extends BaseModel {

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GameType type;

    @Column(nullable = false)
    private Integer winsRequired;

    @OneToOne
    @JoinColumn(name = "gameId")
    @FilterJoinTable(name = "notDeleted", condition = "deletedDate IS NULL")
    private Game game;

    public GameSetting() {
    }

    public GameSetting(final Builder builder) {
        this.type = builder.type;
        this.winsRequired = builder.winsRequired;
        this.game = builder.game;
    }

    public GameType getType() {
        return this.type;
    }

    public void setType(final GameType type) {
        this.type = type;
    }

    public Integer getWinsRequired() {
        return this.winsRequired;
    }

    public void setWinsRequired(final Integer winsRequired) {
        this.winsRequired = winsRequired;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(final Game game) {
        this.game = game;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GameType type;
        private Integer winsRequired;
        private Game game;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withGameType(final GameType type) {
            this.type = type;
            return this;
        }

        public Builder withWinsRequired(final Integer winsRequired) {
            this.winsRequired = winsRequired;
            return this;
        }

        public Builder withGame(final Game game) {
            this.game = game;
            return this;
        }

        public GameSetting build() {
            return new GameSetting(this);
        }
    }

}