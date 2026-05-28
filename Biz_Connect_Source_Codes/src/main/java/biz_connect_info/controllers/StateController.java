package biz_connect_info.controllers;

import biz_connect_info.models.State;
import biz_connect_info.service.State.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "state")
@CrossOrigin(origins = "*")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("/update_state")
    public State updateState(
            @RequestBody State state
    ) {
        return stateService.updateState(state);
    }

    @DeleteMapping("/delete_state/{stateId}")
    public MessageResponse deleteState(@PathVariable Integer stateId)
    {
        return stateService.deleteState(stateId);
    }

    @GetMapping("/get_state_by_id/{stateId}")
    public State getStateById(
            @PathVariable Integer stateId
    ) {
        return stateService.getStateById(stateId);
    }

    @GetMapping("/states_by_country/{countryId}")
    public List<State> getStatesByCountryId(
            @PathVariable Integer countryId
    ) {
        return stateService
                .getStatesByCountryId(countryId);
    }

    @GetMapping("/states_list")
    public List<State> getAllStates() {
        return stateService.getAllStates();
    }
}