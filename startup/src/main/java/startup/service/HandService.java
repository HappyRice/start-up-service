package startup.service;

import startup.common.dto.HandDto;
import startup.exception.GameNotFoundException;
import startup.exception.HandNotFoundException;

public interface HandService {

    HandDto createNewHand(final String gameGuid) throws GameNotFoundException;

    HandDto createFlop(final String handGuid) throws HandNotFoundException;

    HandDto createTurn(final String handGuid) throws HandNotFoundException;

    HandDto createRiver(final String handGuid) throws HandNotFoundException;

}
