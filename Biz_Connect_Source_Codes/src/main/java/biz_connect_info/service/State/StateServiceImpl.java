package biz_connect_info.service.State;

import biz_connect_info.models.State;

import java.util.List;

abstract class StateServiceImpl implements StateService {

    @Override
    public State updateState(State state) {
        return new StateServiceDAL().updateState(state);
    }

    @Override
    public void deleteState(Integer stateId) {
        new StateServiceDAL().deleteState(stateId);
    }

    @Override
    public State getStateById(Integer stateId) {
        return new StateServiceDAL().getStateById(stateId);
    }

    @Override
    public State getStateByStateName(String stateName) {
        return new StateServiceDAL().getStateByStateName(stateName);
    }

    @Override
    public List<State> getStatesByCountryId(Integer countryId) {
        return new StateServiceDAL().getStatesByCountryId(countryId);
    }

    @Override
    public List<State> getAllStates() {
        return new StateServiceDAL().getAllStates();
    }

    @Override
    public List<State> getAllStatesOrderByStateName() {
        return new StateServiceDAL().getAllStatesOrderByStateName();
    }

    @Override
    public boolean existsByStateName(String stateName) {
        return new StateServiceDAL().existsByStateName(stateName);
    }
}