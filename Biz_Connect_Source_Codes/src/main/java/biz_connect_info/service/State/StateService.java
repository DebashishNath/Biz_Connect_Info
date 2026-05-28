package biz_connect_info.service.State;

import biz_connect_info.models.State;
import utils.MessageResponse;

import java.util.List;

public interface StateService {

    State updateState(State state);

    MessageResponse deleteState(Integer stateId);

    State getStateById(Integer stateId);

    State getStateByStateName(String stateName);

    List<State> getStatesByCountryId(Integer countryId);

    List<State> getAllStates();

    List<State> getAllStatesOrderByStateName();

    boolean existsByStateName(String stateName);
}