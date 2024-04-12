package startup.model;

import org.hibernate.annotations.FilterJoinTable;
import startup.common.enumeration.Card;

import javax.persistence.*;

@Entity
@Table(name = "Board")
public class Board extends BaseModel {

    @OneToOne
    @JoinColumn(name = "handId")
    @FilterJoinTable(name = "notDeleted", condition = "deletedDate IS NULL")
    private Hand hand;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Card flop1;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Card flop2;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Card flop3;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Card turn;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Card river;

    public Board() {
    }

    public Board(final Builder builder) {
        this.hand = builder.hand;
        this.flop1 = builder.flop1;
        this.flop2 = builder.flop2;
        this.flop3 = builder.flop3;
        this.turn = builder.turn;
        this.river = builder.river;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(final Hand hand) {
        this.hand = hand;
    }

    public Card getFlop1() {
        return this.flop1;
    }

    public void setFlop1(final Card flop1) {
        this.flop1 = flop1;
    }

    public Card getFlop2() {
        return this.flop2;
    }

    public void setFlop2(final Card flop2) {
        this.flop2 = flop2;
    }

    public Card getFlop3() {
        return this.flop1;
    }

    public void setFlop3(final Card flop3) {
        this.flop3 = flop3;
    }

    public Card getTurn() {
        return this.turn;
    }

    public void setTurn(final Card turn) {
        this.turn = turn;
    }

    public Card getRiver() {
        return this.river;
    }

    public void setRiver(final Card river) {
        this.river = river;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Hand hand;
        private Card flop1;
        private Card flop2;
        private Card flop3;
        private Card turn;
        private Card river;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withHand(final Hand hand) {
            this.hand = hand;
            return this;
        }

        public Builder withFlop1(final Card flop1) {
            this.flop1 = flop1;
            return this;
        }

        public Builder withFlop2(final Card flop2) {
            this.flop2 = flop2;
            return this;
        }
        public Builder withFlop3(final Card flop3) {
            this.flop3 = flop3;
            return this;
        }

        public Builder withTurn(final Card turn) {
            this.turn = turn;
            return this;
        }

        public Builder withRiver(final Card river) {
            this.river = river;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }

}