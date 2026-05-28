package biz_connect_info.service.State;

import biz_connect_info.models.State;
import biz_connect_info.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.util.List;

@Service
class StateServiceDAL extends StateServiceImpl {

    @Autowired
    private StateRepository stateRep;

    public StateServiceDAL() {
    }

    @Override
    public State updateState(State state) {

        MessageResponse msgResp;

        try {

            State stateToUpdate = stateRep.save(state);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "State details updated successfully!"
            );

            stateToUpdate.setReturnMessage(msgResp);

            return stateToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update state details!"
            );

            state.setReturnMessage(msgResp);

            return state;
        }
    }

    @Override
    public MessageResponse deleteState(Integer stateId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            stateRep.deleteById(stateId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "State details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete state");
            return msgResp;
        }
    }

    @Override
    public State getStateById(Integer stateId) {

        try {

            return stateRep
                    .findById(stateId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public State getStateByStateName(String stateName) {

        try {

            return stateRep
                    .findByStateName(stateName)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<State> getStatesByCountryId(Integer countryId) {

        try {

            return stateRep.findByCountryCountryId(countryId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<State> getAllStates() {

        try {

            return stateRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<State> getAllStatesOrderByStateName() {

        try {

            return stateRep.findAllByOrderByStateNameAsc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public boolean existsByStateName(String stateName) {

        try {

            return stateRep
                    .findByStateName(stateName)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return false;
        }
    }
}