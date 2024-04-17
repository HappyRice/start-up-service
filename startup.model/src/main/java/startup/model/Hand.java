package startup.model;

import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.Where;
import startup.common.enumeration.Card;
import startup.common.enumeration.HandState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hand")
@NamedQueries({
        @NamedQuery(name = "getHandByGuid", query = "SELECT hand FROM Hand as hand where guid = :guid")
})
public class Hand extends BaseModel {

    @OneToOne
    @JoinColumn(name = "winnerId")
    private Player winner;

    @ManyToOne
    @JoinColumn(name = "gameId")
    @FilterJoinTable(name = "notDeleted", condition = "deletedDate IS NULL")
    private Game game;

    @Column
    @Enumerated(value = EnumType.STRING)
    private HandState state;

    @ElementCollection(targetClass = Card.class)
    @Column(name = "card", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @CollectionTable(name = "HandDeck", joinColumns = @JoinColumn(name = "handId"))
    private List<Card> cards = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hand")
    @Where(clause = "deletedDate IS NULL")
    private Board board;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hand")
    @Where(clause = "deletedDate IS NULL")
    private List<PlayerHand> playerHands = new ArrayList<>();

    @Column
    private Integer handNumber;

    public Hand() {
    }

    public Hand(final Builder builder) {
        this.winner = builder.winner;
        this.game = builder.game;
        this.state = builder.state;
        this.cards = builder.cards != null ? builder.cards : new ArrayList<>();
        this.board = builder.board;
        this.playerHands = builder.playerHands != null ? builder.build().playerHands : new ArrayList<>();
        this.handNumber = builder.handNumber;
    }

    public Player getWinner() {
        return this.winner;
    }

    public void setWinner(final Player winner) {
        this.winner = winner;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(final Game game) {
        this.game = game;
    }

    public HandState getState() {
        return this.state;
    }

    public void setState(final HandState state) {
        this.state = state;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(final List<Card> cards) {
        this.cards = cards;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(final Board board) {
        this.board = board;
    }

    public List<PlayerHand> getPlayerHands() {
        return this.playerHands;
    }

    public void setPlayerHands(final List<PlayerHand> playerHands) {
        this.playerHands = playerHands;
    }

    public Integer getHandNumber() {
        return this.handNumber;
    }

    public void setHandNumber(final Integer handNumber) {
        this.handNumber = handNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Player winner;
        private Game game;
        private HandState state;
        private List<Card> cards;
        private Board board;
        private List<PlayerHand> playerHands;
        private Integer handNumber;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withWinner(final Player winner) {
            this.winner = winner;
            return this;
        }

        public Builder withGame(final Game game) {
            this.game = game;
            return this;
        }

        public Builder withState(final HandState state) {
            this.state = state;
            return this;
        }

        public Builder withCards(final List<Card> cards) {
            this.cards = cards;
            return this;
        }

        public Builder withBoard(final Board board) {
            this.board = board;
            return this;
        }

        public Builder withPlayerHands(final List<PlayerHand> playerHands) {
            this.playerHands = playerHands;
            return this;
        }

        public Builder withHandNumber(final Integer handNumber) {
            this.handNumber = handNumber;
            return this;
        }

        public Hand build() {
            return new Hand(this);
        }
    }

}