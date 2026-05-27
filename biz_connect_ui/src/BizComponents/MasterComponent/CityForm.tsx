import React, { useCallback, useEffect, useState } from 'react';
import { Table, TableRow, TableCell, Typography, Stack } from '@mui/material';

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
  useAuth,
  ReInputLabel
} from '@kcndigitals/lib';

import { City, Country, State } from '../Interfaces/bizConnectInterfaces';
import { ADD_NEW_CITY, BIZ_CONNECT_API_PATH, EDIT_CITY } from '../Constants/BizConnectConstants';

const CityForm: React.FC = () => {

  const [isMounted, setIsMounted] = useState<boolean>(false);
  const [countries, setCountries] = useState<Country[]>([]);
  const [countryId, setCountryId] = useState<number>(0);
  const [statesToDisplay, setStatesToDisplay] = useState<State[]>([]);
  const [stateId, setStateId] = useState<number>(0);
  const [citiesToDisplay, setCitiesToDisplay] = useState<City[]>([]);
  const [cityId, setCityId] = useState<number>(0);
  const [noOfRecords, setNoOfRecords] = useState<number>(0);
  const [showCityList, setShowCityList] = useState<boolean>(false);
  const [open, setOpen] = useState<boolean>(true);
  const [formHeading, setFormHeading] = useState<string>('');
  
  const adminApi = useAdminApi();

  const { showMessageBox,title,displayMessage,showMessage,handleCloseMessageBox } = useMessageHandler();

  const handleError = useCallback((err: any) => {

    const errorMessage = err instanceof Error ? err.message : String(err);

    showMessage(true, ERROR_MSG_TITLE, errorMessage, '');

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

 
  useEffect(() => {

    if (isMounted) {
      return;
    }

    const init = async () => {
      setIsMounted(true);
      setFormHeading(ADD_NEW_CITY);
      await loadCountries();
      setFocus('countryCombo');
    };
    init();
  }, [isMounted, loadCountries]);

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
    let city_id=null;
    if(cityId > 0)
    {
      city_id=cityId;
    }
    const postData = {
      cityId: city_id,
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
        await displayAllCities(stateId);

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
      setCountryId(cityData.state.country.countryId);
      setFocus('countryCombo');
    }
  };

  const clearCity = async () => 
  {
    setFormHeading(ADD_NEW_CITY);
    setCityId(0);
    setStateId(0);
    setCountryId(0);
    setCitiesToDisplay([]);
    setNoOfRecords(0);
    clearAllTextFields();
    setFocus('countryCombo');
  };

  // Display All States
  const displayAllStates = async (country_id:number) => 
  {
    setStateId(0);
    setStatesToDisplay([]);
    setCitiesToDisplay([]);
    setInputValue("cityName","");
    try {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `state/states_by_country/` + country_id;
      const response = await adminApi.get(url);
      const data: State[] = response.data;

      if (data && data.length > 0) {
        setStatesToDisplay(data);
      }
    } catch (err) {
      handleError(err);
    }
  };

  const displayAllCities = async(state_id:number) => 
  {
    try 
    {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `city/cities_by_state/` + state_id;
      const response = await adminApi.get(url);
      const data: City[] = response.data;
      if (data && data.length > 0) 
      {
        setCitiesToDisplay(data);
        setNoOfRecords(data.length);
        setShowCityList(true);
      }
    } catch (err) 
    {
      handleError(err);
    }
  };

  const country_change=(country_id:number)=>{
    setNoOfRecords(0);
    setCountryId(country_id);
    displayAllStates(country_id);
  }

  const state_change =(state_id:number) =>
  {
    setStateId(state_id);
    displayAllCities(state_id);
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
            <ReSelect id="stateCombo" value={stateId.toString()}  options={statesToDisplay}
              getOptionLabel={(opt) => opt.stateName} getOptionValue={(opt) => opt.stateId}
              onChange={(e) => state_change(Number(e.target.value))} width="80%"
            />
          </TableCell>
        </TableRow>
        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <ReInputLabel labelValue="City" />
          </TableCell>
          <TableCell width={'40%'}>
            <ReTextField id="cityName" type="text" width="80%" />
          </TableCell>
          <TableCell />
          <TableCell colSpan={3} align="left">
            <Stack direction="row" spacing={2} alignItems="center">
              <Typography variant="body2">
                <b>No Of Cities: {noOfRecords}</b>
              </Typography>
              <ReButton id="btnSaveCity" label="Save" buttonType="Save" onClick={saveCity} />
              <ReButton id="btnRefreshCity" label="Clear" buttonType="Refresh" onClick={clearCity} />
            </Stack>
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