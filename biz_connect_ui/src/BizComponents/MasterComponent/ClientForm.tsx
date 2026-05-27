import React, { useCallback, useEffect, useState } from 'react';
import { Table, TableRow, TableCell, Typography } from '@mui/material';

import {Client,Country,State,City} from '../Interfaces/bizConnectInterfaces';

import { useAdminApi,MessageBox,getServerIP,SAVE_MSG_TITLE,ERROR_MSG_TITLE,getInputValue,setInputValue,
  clearAllTextFields,setFocus,useMessageHandler,ListRecords,ReButton,FormTemplate,ReTextField,
  ReInputLabel,ReSelect,useAuth } from '@kcndigitals/lib';
import { ADD_NEW_CLIENT, BIZ_CONNECT_API_PATH, clientTypes, EDIT_CLIENT } from '../Constants/BizConnectConstants';

const ClientForm: React.FC = () => {
  const [isMounted, setIsMounted] = useState<boolean>(false);

  const [clientId, setClientId] = useState<number>(0);
  const [clientsToDisplay, setClientsToDisplay] = useState<Client[]>([]);
  const [showClientList, setShowClientList] = useState<boolean>(false);
  const [noOfRecords, setNoOfRecords] = useState<number>(0);

  const [countries, setCountries] = useState<Country[]>([]);
  const [states, setStates] = useState<State[]>([]);
  const [cities, setCities] = useState<City[]>([]);

  const [countryId, setCountryId] = useState<number>(0);
  const [stateId, setStateId] = useState<number>(0);
  const [cityId, setCityId] = useState<number>(0);

  const [clientType, setClientType] = useState<string>('');

  const [formHeading, setFormHeading] = useState<string>('');
  const [open, setOpen] = useState<boolean>(true);

  const adminApi = useAdminApi();
  const { token, isLoading } = useAuth();

  const {
    showMessageBox,
    title,
    displayMessage,
    showMessage,
    handleCloseMessageBox
  } = useMessageHandler();

  const handleError = useCallback(
    (err: any) => {
      const errorMessage =
        err instanceof Error ? err.message : String(err);

      showMessage(true, ERROR_MSG_TITLE, errorMessage, '');
    },
    [showMessage]
  );

  // =========================
  // Load Countries
  // =========================
  const loadCountries = useCallback(async () => {
    try {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `country/countries_list`;
      const response = await adminApi.get(url);

      if (response.data) {
        setCountries(response.data);
      }
    } catch (err) {
      handleError(err);
    }
  }, [adminApi, handleError]);

  // =========================
  // Load States
  // =========================
  const loadStates = useCallback(
    async (selectedCountryId: number) => {
      try {
        const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `state/states_by_country/${selectedCountryId}`;
        const response = await adminApi.get(url);

        if (response.data) {
          setStates(response.data);
        }
      } catch (err) {
        handleError(err);
      }
    },
    [adminApi, handleError]
  );

  // =========================
  // Load Cities
  // =========================
  const loadCities = useCallback(
    async (selectedStateId: number) => {
      try {
        const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `city/cities_by_state/${selectedStateId}`;
        const response = await adminApi.get(url);

        if (response.data) {
          setCities(response.data);
        }
      } catch (err) {
        handleError(err);
      }
    },
    [adminApi, handleError]
  );

  // =========================
  // On Mount
  // =========================
  useEffect(() => {
    if (isMounted) {
      return;
    }

    const init = async () => {
      setIsMounted(true);
      setFormHeading(ADD_NEW_CLIENT);

      await loadCountries();

      setFocus('clientType');
    };

    init();
  }, [isMounted, loadCountries]);

  if (!open) return null;

  // =========================
  // Validation
  // =========================
  const validateBeforeSave = async () => {
    if (clientType === '') {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Select client type',
        'clientType'
      );
      return false;
    }

    if (getInputValue('companyName', 'string') === '') {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Company name cannot be empty',
        'companyName'
      );
      return false;
    }

    if (getInputValue('contactPersonName', 'string') === '') {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Contact person name cannot be empty',
        'contactPersonName'
      );
      return false;
    }

    if (getInputValue('mobileNo', 'string') === '') {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Mobile no cannot be empty',
        'mobileNo'
      );
      return false;
    }

    if (countryId === 0) {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Select country',
        'countryCombo'
      );
      return false;
    }

    if (stateId === 0) {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Select state',
        'stateCombo'
      );
      return false;
    }

    if (cityId === 0) {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Select city',
        'cityCombo'
      );
      return false;
    }

    return true;
  };

  // =========================
  // Save Client
  // =========================
  const saveClient = async () => {
    if (!(await validateBeforeSave())) {
      return;
    }
    let client_id=null;
    if(clientId > 0)
    {
      client_id=clientId;
    }
    const postData = {
      clientId: client_id,
      clientType: clientType,
      companyName: getInputValue('companyName', 'string'),
      contactPersonName: getInputValue('contactPersonName','string'),
      email: getInputValue('email', 'string'),
      mobileNo: getInputValue('mobileNo', 'string'),
      whatsappNo: getInputValue('whatsappNo', 'string'),
      website: getInputValue('website', 'string'),
      address: getInputValue('address', 'string'),
      city: { cityId: cityId },
      postalCode: getInputValue('postalCode', 'string'),
      remarks: getInputValue('remarks', 'string')
    };

    try {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `client/update_client`;

      const response = await adminApi.post(url, postData);

      const clientData = response.data;

      if (clientData.returnMessage.code === 0) {
        showMessage(
          true,
          SAVE_MSG_TITLE,
          clientData.returnMessage.message,
          ''
        );

        setFormHeading(EDIT_CLIENT);

        await displayAllClients();
      } else {
        showMessage(
          true,
          ERROR_MSG_TITLE,
          clientData.returnMessage.message,
          ''
        );
      }
    } catch (error) {
      handleError(error);
    }
  };

  // =========================
  // Edit Client
  // =========================
  const doEditOfClient = async (editClientId: number) => {
    const clientData = clientsToDisplay.find(
      (client) => client.clientId === editClientId
    );

    if (clientData) {
      setFormHeading(EDIT_CLIENT);

      setClientId(editClientId);

      setClientType(clientData.clientType);

      setInputValue('companyName', clientData.companyName);
      setInputValue(
        'contactPersonName',
        clientData.contactPersonName
      );
      setInputValue('email', clientData.email);
      setInputValue('mobileNo', clientData.mobileNo);
      setInputValue('whatsappNo', clientData.whatsappNo);
      setInputValue('website', clientData.website);
      setInputValue('address', clientData.address);
      setInputValue('postalCode', clientData.postalCode);
      setInputValue('remarks', clientData.remarks);

      const selectedCountryId = clientData.city.state.country.countryId;

      const selectedStateId = clientData.city.state.stateId;

      const selectedCityId = clientData.city.cityId;

      setCountryId(selectedCountryId);

      await loadStates(selectedCountryId);

      setStateId(selectedStateId);

      await loadCities(selectedStateId);

      setCityId(selectedCityId);
    }
  };

  // =========================
  // Clear Form
  // =========================
  const clearClient = async () => {
    clearAllTextFields();

    setClientId(0);

    setCountryId(0);
    setStateId(0);
    setCityId(0);

    setStates([]);
    setCities([]);

    setClientType('');

    setFormHeading(ADD_NEW_CLIENT);

    setFocus('companyName');
  };

  // =========================
  // Display Clients
  // =========================
  const displayAllClients = async () => {
    if (isLoading || !token) {
      return;
    }

    try {
      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `client/clients_list`;

      const response = await adminApi.get(url);

      const data: Client[] = await response.data;

      if (data && data.length > 0) {
        setClientsToDisplay(data);
        setNoOfRecords(data.length);
        setShowClientList(true);
      }
    } catch (err) {
      handleError(err);
    }
  };

  return (
    <FormTemplate width={'90%'} heading={formHeading} onClose={() => setOpen(false)} >
      <MessageBox open={showMessageBox} onClose={handleCloseMessageBox} title={title}>
        {displayMessage}
      </MessageBox>

      <Table size="small">
        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <ReInputLabel labelValue="Client Type" />
          </TableCell>

          <TableCell>
            <ReSelect id="clientType" value={clientType} options={clientTypes} getOptionLabel={(opt) => opt.value}
              getOptionValue={(opt) => opt.id} onChange={(e) => setClientType(e.target.value) } width="200px" />
          </TableCell>

          <TableCell>
            <ReInputLabel labelValue="Company Name" />
          </TableCell>

          <TableCell>
            <ReTextField id="companyName" type="text" width="250px"/>
          </TableCell>

          <TableCell>
            <ReInputLabel labelValue="Contact Person" />
          </TableCell>

          <TableCell>
            <ReTextField id="contactPersonName" type="text" width="250px" />
          </TableCell>
        </TableRow>

        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <ReInputLabel labelValue="Email" />
          </TableCell>

          <TableCell>
            <ReTextField id="email" type="email" width="200px" />
          </TableCell>

          <TableCell>
            <ReInputLabel labelValue="Mobile No" />
          </TableCell>

          <TableCell>
            <ReTextField
              id="mobileNo"
              type="text"
              width="200px"
            />
          </TableCell>

          <TableCell>
            <ReInputLabel labelValue="Whatsapp No" />
          </TableCell>

          <TableCell>
            <ReTextField
              id="whatsappNo"
              type="text"
              width="200px"
            />
          </TableCell>
          <TableCell>
            <ReInputLabel labelValue="Website" />
          </TableCell>

          <TableCell>
            <ReTextField
              id="website"
              type="text"
              width="200px"
            />
          </TableCell>
        </TableRow>

        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <ReInputLabel labelValue="Country" />
          </TableCell>

          <TableCell>
            <ReSelect id="countryCombo" value={countryId.toString()} options={countries}
              getOptionLabel={(opt) => opt.countryName} getOptionValue={(opt) => opt.countryId}
              onChange={async (e) => {
                const selectedCountryId = Number(
                  e.target.value
                );
                setCountryId(selectedCountryId);
                setStateId(0);
                setCityId(0);
                setCities([]);
                await loadStates(selectedCountryId);
              }}
              width="200px"
            />
          </TableCell>
          <TableCell>
            <ReInputLabel labelValue="State" />
          </TableCell>

          <TableCell>
            <ReSelect id="stateCombo" value={stateId.toString()} options={states}
              getOptionLabel={(opt) => opt.stateName} getOptionValue={(opt) => opt.stateId}
              onChange={async (e) => {
                const selectedStateId = Number(
                  e.target.value
                );
                setStateId(selectedStateId);
                setCityId(0);
                await loadCities(selectedStateId);
              }}
              width="200px"
            />
          </TableCell>
           <TableCell>
            <ReInputLabel labelValue="City" />
          </TableCell>

          <TableCell>
            <ReSelect
              id="cityCombo"
              value={cityId.toString()}
              options={cities}
              getOptionLabel={(opt) => opt.cityName}
              getOptionValue={(opt) => opt.cityId}
              onChange={(e) =>
                setCityId(Number(e.target.value))
              }
              width="200px"
            />
          </TableCell>
           <TableCell>
            <ReInputLabel labelValue="Postal Code" />
          </TableCell>

          <TableCell>
            <ReTextField
              id="postalCode"
              type="text"
              width="200px"
            />
          </TableCell>
        </TableRow>

        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <ReInputLabel labelValue="Address" />
          </TableCell>

          <TableCell colSpan={3}>
            <ReTextField
              id="address"
              type="multiline"
              rows={3}
              width="100%"
            />
          </TableCell>
        </TableRow>

        <TableRow sx={{ '& td, & th': { borderBottom: 'none' } }}>
          <TableCell>
            <ReInputLabel labelValue="Remarks" />
          </TableCell>

          <TableCell colSpan={3}>
            <ReTextField
              id="remarks"
              type="multiline"
              rows={2}
              width="100%"
            />
          </TableCell>
          <TableCell />
          <TableCell align='right'>
            <Typography variant="body2">
              <b>No Of Clients: {noOfRecords}</b>
            </Typography>
          </TableCell>
          <TableCell colSpan={3} align="right">
            <ReButton
              id="btnDisplayClients"
              label="Display Clients"
              buttonType="Display"
              onClick={displayAllClients}
            />
            &nbsp;&nbsp;

            <ReButton
              id="btnSaveClient"
              label="Save"
              buttonType="Save"
              onClick={saveClient}
            />
            &nbsp;&nbsp;

            <ReButton
              id="btnClearClient"
              label="Clear"
              buttonType="Refresh"
              onClick={clearClient}
            />
          </TableCell>
        </TableRow>
      </Table>

      <ListRecords
        showList={showClientList}
        data={clientsToDisplay}
        columns={[
          {
            label: 'Company',
            valueAccessor: (c: Client) => c.companyName
          },
          {
            label: 'Contact Person',
            valueAccessor: (c: Client) =>
              c.contactPersonName
          },
          {
            label: 'Client Type',
            valueAccessor: (c: Client) =>
              c.clientType
          },
          {
            label: 'Mobile No',
            valueAccessor: (c: Client) =>
              c.mobileNo
          },
          {
            label: 'Email',
            valueAccessor: (c: Client) =>
              c.email
          },
          {
            label: 'City',
            valueAccessor: (c: Client) =>
              c.city?.cityName
          },
          {
            label: 'State',
            valueAccessor: (c: Client) =>
              c.city?.state?.stateName
          },
          {
            label: 'Country',
            valueAccessor: (c: Client) =>
              c.city?.state?.country?.countryName
          }
        ]}
        onEdit={(client: Client) =>
          doEditOfClient(client.clientId)
        }
        showEditColumn={true}
        showDeleteColumn={false}
        noRecordsMessage={'No clients to display'}
      />
    </FormTemplate>
  );
};

export default ClientForm;