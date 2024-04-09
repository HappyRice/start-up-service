package startup.model;

import org.hibernate.annotations.FilterJoinTable;
import startup.model.enumeration.Card;

import javax.persistence.*;

@Entity
@Table(name = "PlayerHand")
public class PlayerHand extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "handId")
    @FilterJoinTable(name = "notDeleted", condition = "deletedDate IS NULL")
    private Hand hand;

    @ManyToOne
    @JoinColumn(name = "playerId")
    @FilterJoinTable(name = "notDeleted", condition = "deletedDate IS NULL")
    private Player player;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Card card1;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Card card2;

    public PlayerHand() {
    }

    public PlayerHand(final Builder builder) {
        this.hand = builder.hand;
        this.player = builder.player;
        this.card1 = builder.card1;
        this.card2 = builder.card2;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(final Hand hand) {
        this.hand = hand;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public Card getCard1() {
        return this.card1;
    }

    public void setCard1(final Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return this.card2;
    }

    public void setCard2(final Card card2) {
        this.card2 = card2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Hand hand;
        private Player player;
        private Card card1;
        private Card card2;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withHand(final Hand hand) {
            this.hand = hand;
            return this;
        }

        public Builder withPlayer(final Player player) {
            this.player = player;
            return this;
        }

        public Builder withCard1(final Card card1) {
            this.card1 = card1;
            return this;
        }

        public Builder withCard2(final Card card2) {
            this.card2 = card2;
            return this;
        }

        public PlayerHand build() {
            return new PlayerHand(this);
        }
    }

}