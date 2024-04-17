package startup.service;

import startup.common.dto.HandDto;
import startup.exception.GameNotFoundException;
import startup.exception.HandNotFoundException;
import startup.exception.InvalidHandStateException;

public interface HandService {

    HandDto createNewHand(final String gameGuid) throws GameNotFoundException, InvalidHandStateException;

    HandDto createFlop(final String handGuid) throws HandNotFoundException, InvalidHandStateException;

    HandDto createTurn(final String handGuid) throws HandNotFoundException, InvalidHandStateException;

    HandDto createRiver(final String handGuid) throws HandNotFoundException, InvalidHandStateException;

}
