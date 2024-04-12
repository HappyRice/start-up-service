package startup.transformer;

import startup.common.dto.BoardDto;
import startup.model.Board;

public final class BoardTransformer {

    private BoardTransformer() {
        // Prevent Instantiation
    }

    public static BoardDto buildBoardDto(final Board board) {
        if (board != null) {
            return BoardDto.builder()
                    .withFlop1(board.getFlop1())
                    .withFlop2(board.getFlop2())
                    .withFlop3(board.getFlop3())
                    .withTurn(board.getTurn())
                    .withRiver(board.getRiver())
                    .build();
        } else {
            return null;
        }
    }

}