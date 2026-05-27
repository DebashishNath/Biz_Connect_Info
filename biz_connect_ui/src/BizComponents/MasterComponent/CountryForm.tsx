import React, { useCallback, useEffect, useState } from 'react';
import { Table, TableRow, TableCell, Typography } from '@mui/material';
import { Country } from '../Interfaces/bizConnectInterfaces';

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
  useAuth
} from '@kcndigitals/lib';
import { ADD_NEW_COUNTRY, BIZ_CONNECT_API_PATH, EDIT_COUNTRY } from '../Constants/BizConnectConstants';

const CountryForm: React.FC = () => {

  const [isMounted, setIsMounted] = useState<boolean>(false);
  const [countriesToDisplay, setCountriesToDisplay] = useState<Country[]>([]);
  const [countryId, setCountryId] = useState<number | null>(null);
  const [noOfRecords, setNoOfRecords] = useState<number>(0);
  const [showCountryList, setShowCountryList] = useState<boolean>(false);
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
    const errorMessage =
      err instanceof Error ? err.message : String(err);

    showMessage(
      true,
      ERROR_MSG_TITLE,
      errorMessage,
      ''
    );
  }, [showMessage]);

  // Initial Load
  useEffect(() => {

    if (isMounted) {
      return;
    }

    const init = async () => {
      setIsMounted(true);
      setFormHeading(ADD_NEW_COUNTRY);
      setFocus('countryName');
    };

    init();

  }, [isMounted]);

  if (!open) {
    return null;
  }

  // Validation
  const validateBeforeSave = async () => {

    if (getInputValue('countryName', 'string') === '') {
      showMessage(
        true,
        ERROR_MSG_TITLE,
        'Country name cannot be empty',
        'countryName'
      );
      return false;
    }

    return true;
  };

  // Save Country
  const saveCountry = async () => {

    if (!await validateBeforeSave()) {
      return;
    }

    const postData = {
      countryId: countryId,
      countryName: getInputValue('countryName', 'string')
    };

    try {

      const url = `${getServerIP()}` + BIZ_CONNECT_API_PATH + `country/update_country`;

      const response = await adminApi.post(url, postData);

      const countryData = response.data;

      if (countryData.returnMessage.code === 0) {

        showMessage(
          true,
          SAVE_MSG_TITLE,
          countryData.returnMessage.message,
          ''
        );

        setFormHeading(EDIT_COUNTRY);

        if (countryData.countryId) {
          setCountryId(countryData.countryId);
        }

        await displayAllCountries();

      } else {

        showMessage(
          true,
          ERROR_MSG_TITLE,
          countryData.returnMessage.message,
          ''
        );
      }

    } catch (error) {
      handleError(error);
    }
  };

  // Edit Country
  const doEditOfCountry = async (editCountryId: number) => {

    const countryData = countriesToDisplay.find(
      (country) => country.countryId === editCountryId
    );

    if (countryData) {

      setFormHeading(EDIT_COUNTRY);

      setCountryId(countryData.countryId);

      setInputValue(
        'countryName',
        countryData.countryName
      );

      setFocus('countryName');
    }
  };

  // Clear Form
  const clearCountry = async () => {

    setFormHeading(ADD_NEW_COUNTRY);

    setCountryId(null);

    clearAllTextFields();

    setFocus('countryName');
  };

  // Display All Countries
  const displayAllCountries = async () => {

    if (isLoading || !token) {
      return;
    }

    try {

      const url = `${getServerIP()}`+ BIZ_CONNECT_API_PATH + `country/countries_list`;

      const response = await adminApi.get(url);

      const data: Country[] = response.data;

      if (data && data.length > 0) {

        setCountriesToDisplay(data);

        setNoOfRecords(data.length);

        setShowCountryList(true);
      }

    } catch (err) {
      handleError(err);
    }
  };

  return (
    <FormTemplate
      width={'30%'}
      heading={formHeading}
      onClose={() => setOpen(false)}
    >

      <MessageBox
        open={showMessageBox}
        onClose={handleCloseMessageBox}
        title={title}
      >
        {displayMessage}
      </MessageBox>

      <Table size="small">

        <TableRow
          sx={{ '& td, & th': { borderBottom: 'none' } }}
        >

          <TableCell width={'40%'}>
            <ReTextField
              id="countryName"
              type="text"
              label="Country Name"
              width="100%"
            />
          </TableCell>

        </TableRow>

        <TableRow
          sx={{ '& td, & th': { borderBottom: 'none' } }}
        >

          <TableCell>
            <Typography variant="body2">
              <b>No Of Countries: {noOfRecords}</b>
            </Typography>
          </TableCell>

        </TableRow>

        <TableRow
          sx={{ '& td, & th': { borderBottom: 'none' } }}
        >

          <TableCell align="right">

            <ReButton
              id="btnDisplayCountries"
              label="Display Countries"
              buttonType="Display"
              onClick={displayAllCountries}
            />

            &nbsp;&nbsp;

            <ReButton
              id="btnSaveCountry"
              label="Save"
              buttonType="Save"
              onClick={saveCountry}
            />

            &nbsp;&nbsp;

            <ReButton
              id="btnRefreshCountry"
              label="Clear"
              buttonType="Refresh"
              onClick={clearCountry}
            />

          </TableCell>

        </TableRow>

      </Table>

      <ListRecords
        showList={showCountryList}
        data={countriesToDisplay}
        columns={[
          {
            label: 'Country Name',
            valueAccessor: (s: Country) => s.countryName
          }
        ]}
        onEdit={(country: Country) =>
          doEditOfCountry(country.countryId)
        }
        showEditColumn={true}
        showDeleteColumn={false}
        noRecordsMessage={'No countries to display'}
      />

    </FormTemplate>
  );
};

export default CountryForm;