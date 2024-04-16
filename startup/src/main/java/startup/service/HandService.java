package startup.service;

import startup.common.dto.HandDto;
import startup.exception.GameNotFoundException;
import startup.exception.HandNotFoundException;
import startup.exception.InvalidHandStateTransitionException;

public interface HandService {

    HandDto createNewHand(final String gameGuid) throws GameNotFoundException, InvalidHandStateTransitionException;

    HandDto createFlop(final String handGuid) throws HandNotFoundException, InvalidHandStateTransitionException;

    HandDto createTurn(final String handGuid) throws HandNotFoundException, InvalidHandStateTransitionException;

    HandDto createRiver(final String handGuid) throws HandNotFoundException, InvalidHandStateTransitionException;

}
