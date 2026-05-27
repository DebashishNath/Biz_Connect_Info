import React, { useCallback, useEffect, useState } from 'react';
import { Table, TableRow, TableCell, Typography } from '@mui/material';

import {
  useAdminApi,
  MessageBox,
  getServerIP,
  SAVE_MSG_TITLE,
  ERROR_MSG_TITLE,
  getInputValue,
  setInputValue,
  clearAllTextFields,
  setFocus,
  useMessageHandler,
  ListRecords,
  ReButton,
  FormTemplate,
  ReTextField,
  ReSelect,
  useAuth
} from '@kcndigitals/lib';

import { City, State } from '../Interfaces/bizConnectInterfaces';
import { BIZ_CONNECT_API_PATH } from '../Constants/BizConnectConstants';

const ADD_NEW_CITY = 'Add New City';
const EDIT_CITY = 'Edit City';

const CityForm: React.FC = () => {

  const [isMounted, setIsMounted] = useState<boolean>(false);
  const [citiesToDisplay, setCitiesToDisplay] = useState<City[]>([]);
  const [states, setStates] = useState<State[]>([]);
  const [cityId, setCityId] = useState<number | null>(null);
  const [stateId, setStateId] = useState<number>(0);
  const [noOfRecords, setNoOfRecords] = useState<number>(0);
  const [showCityList, setShowCityList] = useState<boolean>(false);
  const [open, setOpen] = useState<boolean>(true);
  const [formHeading, setFormHeading] = useState<string>('');

  const adminApi = useAdminApi();
  const { token, isLoading } = useAuth();

  const {
    showMessageBox,
    title,
    displayMessage,
    showMessage,
    handleCloseMessageBox
  } = useMessageHandler();

  const handleError = useCallback((err: any) => {

    const errorMessage = err instanceof Error ? err.message : String(err);

    showMessage(true, ERROR_MSG_TITLE, errorMessage, '');

  }, [showMessage]);

  const loadStates = useCallback(async () => {

    try {

      const url = `${getServerIP()}api/bizconnect/state/states_list`;

      const response = await adminApi.get(url);

      const data: State[] = response.data;

      if (data) {
        setStates(data);
      }

    } catch (err) {
      handleError(err);
    }

  }, [adminApi, handleError]);

  useEffect(() => {

    if (isMounted) {
      return;
    }

    const init = async () => {

      setIsMounted(true);
      setFormHeading(ADD_NEW_CITY);

      await loadStates();

      setFocus('cityName');
    };

    init();

  }, [isMounted, loadStates]);

  if (!open) return null;

  const validateBeforeSave = async () => {

    if (getInputValue('cityName', 'string') === '') {

      showMessage(true, ERROR_MSG_TITLE, 'City name cannot be empty', 'cityName');

      return false;
    }

    if (stateId === 0) {

      showMessage(true, ERROR_MSG_TITLE, 'Select a state', 'stateCombo');

      return false;
    }

    return true;
  };

  const saveCity = async () => {

    if (!await validateBeforeSave()) {
      return;
    }

    const postData = {
      cityId: cityId,
      cityName: getInputValue('cityName', 'string'),
      state: { stateId: stateId }
    };

    try 
    {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `city/update_city`;
      const response = await adminApi.post(url, postData);
      const cityData = response.data;
      if (cityData.returnMessage.code === 0) {
        showMessage(true, SAVE_MSG_TITLE, cityData.returnMessage.message, '');
        setFormHeading(EDIT_CITY);
        if (cityData.cityId) {
          setCityId(cityData.cityId);
        }
        await displayAllCities();

      } else {

        showMessage(true, ERROR_MSG_TITLE, cityData.returnMessage.message, '');
      }

    } catch (error) {

      handleError(error);
    }
  };

  const doEditOfCity = async (editCityId: number) => {

    const cityData = citiesToDisplay.find(
      (city) => city.cityId === editCityId
    );

    if (cityData) {

      setFormHeading(EDIT_CITY);

      setCityId(cityData.cityId);

      setInputValue('cityName', cityData.cityName);

      setStateId(cityData.state.stateId);

      setFocus('cityName');
    }
  };

  const clearCity = async () => 
  {
    setFormHeading(ADD_NEW_CITY);
    setCityId(0);
    setStateId(0);
    clearAllTextFields();
    setFocus('cityName');
  };

  const displayAllCities = async () => {

    if (isLoading || !token) {
      return;
    }

    try {

      const url = `${getServerIP()}api/bizconnect/city/cities_by_state/` + stateId;

      const response = await adminApi.get(url);

      const data: City[] = response.data;

      if (data && data.length > 0) {

        setCitiesToDisplay(data);

        setNoOfRecords(data.length);

        setShowCityList(true);
      }

    } catch (err) {

      handleError(err);
    }
  };

  return (
    <FormTemplate width={'50%'} heading={formHeading} onClose={() => setOpen(false)} >
      <MessageBox open={showMessageBox} onClose={handleCloseMessageBox} title={title}>
        {displayMessage}
      </MessageBox>
      <Table size="small">
        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell width={'40%'}>
            <ReTextField id="cityName" type="text" label="City Name" width="100%" />
          </TableCell>
          <TableCell width={'40%'}>
            <ReSelect id="stateCombo" value={stateId.toString()}  options={states}
              getOptionLabel={(opt) => opt.stateName} getOptionValue={(opt) => opt.stateId}
              onChange={(e) => setStateId(Number(e.target.value))} width="100%"
            />
          </TableCell>
        </TableRow>
        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <Typography variant="body2">
              <b>No Of Cities: {noOfRecords}</b>
            </Typography>
          </TableCell>
        </TableRow>
        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell colSpan={2} align="right">
            <ReButton id="btnDisplayCities" label="Display Cities" buttonType="Display"
              onClick={displayAllCities}
            />
            &nbsp;&nbsp;
            <ReButton id="btnSaveCity" label="Save" buttonType="Save" onClick={saveCity}/>
            &nbsp;&nbsp;
            <ReButton id="btnRefreshCity" label="Clear" buttonType="Refresh" onClick={clearCity}/>
          </TableCell>
        </TableRow>
      </Table>

      <ListRecords showList={showCityList} data={citiesToDisplay}
        columns={[
          { label: 'City Name',valueAccessor: (s: City) => s.cityName },
          { label: 'State', valueAccessor: (s: City) => s.state.stateName },
          { label: 'Country',valueAccessor: (s: City) => s.state.country.countryName }
        ]}
        onEdit={(city: City) => doEditOfCity(city.cityId)}
        showEditColumn={true}
        showDeleteColumn={false}
        noRecordsMessage={'No cities to display'}
      />

    </FormTemplate>
  );
};

export default CityForm;