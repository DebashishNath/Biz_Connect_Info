import React, { useCallback, useEffect, useState } from 'react';
import { Table, TableRow, TableCell, Typography } from '@mui/material';

import { useAdminApi,MessageBox,getServerIP,SAVE_MSG_TITLE,ERROR_MSG_TITLE,getInputValue,setInputValue,clearAllTextFields,
  setFocus,useMessageHandler,ListRecords,ReButton,FormTemplate,ReTextField,ReSelect,useAuth, 
  ReInputLabel} from '@kcndigitals/lib';

import { Country, State } from '../Interfaces/bizConnectInterfaces';
import { ADD_NEW_STATE, BIZ_CONNECT_API_PATH, EDIT_STATE } from '../Constants/BizConnectConstants';

const StateForm: React.FC = () => {

  const [isMounted, setIsMounted] = useState<boolean>(false);
  const [statesToDisplay, setStatesToDisplay] = useState<State[]>([]);
  const [countries, setCountries] = useState<Country[]>([]);
  const [stateId, setStateId] = useState<number | null>(null);
  const [countryId, setCountryId] = useState<number>(0);
  const [noOfRecords, setNoOfRecords] = useState<number>(0);
  const [showStateList, setShowStateList] = useState<boolean>(false);
  const [open, setOpen] = useState<boolean>(true);
  const [formHeading, setFormHeading] = useState<string>('');

  const adminApi = useAdminApi();

  const {
    showMessageBox,
    title,
    displayMessage,
    showMessage,
    handleCloseMessageBox
  } = useMessageHandler();

  const handleError = useCallback((err: any) => {

    const errorMessage =
      err instanceof Error ? err.message : String(err);

    showMessage(
      true,
      ERROR_MSG_TITLE,
      errorMessage,
      ''
    );

  }, [showMessage]);

  // Load Countries
  const loadCountries = useCallback(async () => {
    try {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `country/countries_list`;
      const response = await adminApi.get(url);
      const data: Country[] = response.data;

      if (data) {
        setCountries(data);
      }

    } catch (err) {
      handleError(err);
    }

  }, [adminApi, handleError]);

  // Initial Load
  useEffect(() => {

    if (isMounted) {
      return;
    }

    const init = async () => {

      setIsMounted(true);

      setFormHeading(ADD_NEW_STATE);

      await loadCountries();

      setFocus('countryCombo');
    };

    init();

  }, [isMounted, loadCountries]);

  if (!open) {
    return null;
  }

  // Validation
  const validateBeforeSave = async () => {

    if (getInputValue('stateName', 'string') === '') {

      showMessage(
        true,
        ERROR_MSG_TITLE,
        'State name cannot be empty',
        'stateName'
      );

      return false;
    }

    if (countryId === 0) {

      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Select a country',
        'countryCombo'
      );

      return false;
    }

    return true;
  };

  // Save State
  const saveState = async () => {
    if (!await validateBeforeSave()) {
      return;
    }
    const postData = 
    {
      stateId: stateId,
      stateName: getInputValue('stateName', 'string'),
      country: { countryId: countryId }
    };

    try {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `state/update_state`;
      const response = await adminApi.post(url, postData);
      const stateData = response.data;
      if (stateData.returnMessage.code === 0) 
      {
        showMessage(true,SAVE_MSG_TITLE,stateData.returnMessage.message,'');
        setFormHeading(EDIT_STATE);
        if (stateData.stateId) {
          setStateId(stateData.stateId);
           await displayAllStates(stateData.stateId);
        }
      } 
      else 
      {
        showMessage(true,ERROR_MSG_TITLE,stateData.returnMessage.message,'');
      }

    } catch (error) {
      handleError(error);
    }
  };

  // Edit State
  const doEditOfState = async (editStateId: number) => {

    const stateData = statesToDisplay.find(
      (state) => state.stateId === editStateId
    );

    if (stateData) 
    {
      setFormHeading(EDIT_STATE);
      setStateId(stateData.stateId);
      setInputValue('stateName',stateData.stateName);
      setCountryId(stateData.country.countryId);
      setFocus('stateName');
    }
  };

  // Clear State
  const clearState = async () => {
    setFormHeading(ADD_NEW_STATE);
    setStateId(0);
    setCountryId(0);
    clearAllTextFields();
    setFocus('countryCombo');
  };

  // Display All States
  const displayAllStates = async (country_id:number) => {
    setStatesToDisplay([]);
    setInputValue("stateName","");
    try {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `state/states_by_country/` + country_id;
      const response = await adminApi.get(url);
      const data: State[] = response.data;

      if (data && data.length > 0) {
        setStatesToDisplay(data);
        setNoOfRecords(data.length);
        setShowStateList(true);
      }
    } catch (err) {
      handleError(err);
    }
  };

  const country_change=(country_id:number)=>{
    setCountryId(country_id);
    displayAllStates(country_id);
  }

  return (
    <FormTemplate width={'50%'} heading={formHeading} onClose={() => setOpen(false)} >
      <MessageBox open={showMessageBox} onClose={handleCloseMessageBox} title={title}>
        {displayMessage}
      </MessageBox>
      <Table size="small">
        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <ReInputLabel labelValue="Country" />
          </TableCell>
          <TableCell width={'40%'}>
            <ReSelect id="countryCombo" value={countryId.toString()} options={countries}
              getOptionLabel={(opt) => opt.countryName} getOptionValue={(opt) => opt.countryId}
              onChange={(e) => country_change (Number(e.target.value))} width="80%"
            />
          </TableCell>
          <TableCell>
            <ReInputLabel labelValue="State" />
          </TableCell>
          <TableCell width={'40%'}>
            <ReTextField id="stateName" type="text" label="State Name" />
          </TableCell>
        </TableRow>
        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell colSpan={2} align="left">
            <Typography variant="body2">
              <b>No Of States: {noOfRecords}</b>
            </Typography>
          </TableCell>
          <TableCell colSpan={2} align="right">
            <ReButton id="btnSaveState" label="Save" buttonType="Save" onClick={saveState} />
            &nbsp;&nbsp;
            <ReButton id="btnRefreshState" label="Clear" buttonType="Refresh" onClick={clearState} />
          </TableCell>
        </TableRow> 
      </Table>

      <ListRecords showList={showStateList} data={statesToDisplay}
        columns={[
          { label: 'Country',valueAccessor: (s: State) => s.country.countryName },
          { label: 'State Name', valueAccessor: (s: State) => s.stateName }
        ]}
        onEdit={(state: State) => doEditOfState(state.stateId)}
        showEditColumn={true}
        showDeleteColumn={false}
        noRecordsMessage={'No states to display'}
      />
    </FormTemplate>
  );
};

export default StateForm;