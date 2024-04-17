package startup.transformer;

import startup.common.dto.BoardDto;
import startup.model.Board;

import java.util.Optional;

public final class BoardTransformer {

    private BoardTransformer() {
        // Prevent Instantiation
    }

    public static BoardDto buildBoardDto(final Board board) {
        return Optional.ofNullable(board).map(b -> BoardDto.builder()
                        .withFlop1(board.getFlop1())
                        .withFlop2(board.getFlop2())
                        .withFlop3(board.getFlop3())
                        .withTurn(board.getTurn())
                        .withRiver(board.getRiver())
                        .build()).orElse(null);
    }

}