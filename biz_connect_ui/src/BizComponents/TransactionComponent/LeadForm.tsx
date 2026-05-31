import { useCallback, useEffect, useState } from "react";
import { ERROR_MSG_TITLE,SAVE_MSG_TITLE,DELETE_MSG_TITLE,FormTemplate,FormContent,FormCell,MessageBox,
  ConfirmDialogBox,ReInputLabel,ReSelect,ReTextField,ReButton,ListRecords,useAdminApi,useMessageHandler,
  getServerIP,setFocus,clearAllTextFields,clearSingleTextField,getInputValue,setInputValue } from "@kcndigitals/lib";
import { Box} from "@mui/material";
import {Lead,LeadStatus,LeadSource,Product,Country, Client } from "../Interfaces/bizConnectInterfaces";

import { useLeadMasterInitData } from "../Hooks/useLeadMasterInitData";

import { ADD_LEAD,EDIT_LEAD,BIZ_CONNECT_API_PATH } from "../Constants/BizConnectConstants";

const LeadForm = () => {
  const adminApi = useAdminApi();
  const [open, setOpen] = useState(true);
  const [formHeading, setFormHeading] = useState<string>(ADD_LEAD);
  const [leadId, setLeadId] = useState<number>(0);
  const [mobileNo, setMobileNo] = useState<string>("");
  const [emailId, setEmailId] = useState<string>("");
  const [countryId, setCountryId] = useState<number>(0);
  const [clientId, setClientId] = useState<number>(0);
  const [productId, setProductId] = useState<number>(0);
  const [leadStatusId, setLeadStatusId] = useState<number>(0);
  const [leadSourceId, setLeadSourceId] = useState<number>(0);
  const [expectedBudget, setExpectedBudget] = useState<number>(0);
  const [priorityLevel, setPriorityLevel] = useState<string>("MEDIUM");
  const [showLeadList, setShowLeadList] = useState<boolean>(false);
  const [leadList, setLeadList] = useState<Lead[]>([]);
  const [clientList, setClientList] = useState<Client[]>([]);
  const [deleteLeadId, setDeleteLeadId] = useState<number>(0);
  const [isCountryDisabled, setIsCountryDisabled] = useState<boolean>(false);
  const [isClientDisabled, setIsClientDisabled] = useState<boolean>(false);
  const [showDialogBox, setShowDialogBox] = useState<boolean>(false);
  const { showMessageBox,title,displayMessage,showMessage,handleCloseMessageBox } = useMessageHandler();
  const { countries,products,leadStatuses,leadSources,loading,error } = useLeadMasterInitData();

  const handleError = useCallback(
    (err: any) => {
      const errorMessage = err instanceof Error ? err.message : String(err);
      showMessage(true,ERROR_MSG_TITLE,errorMessage,"");
    },
    [showMessage]
  );

  useEffect(() => {
    setFocus("countriesCombo");
  }, []);

  const displayLeadRecords = async (country_id:number) => 
  {
    setLeadList([]);
    try 
    {
      const url = getServerIP() + BIZ_CONNECT_API_PATH + "lead/get_all_leads_by_country/" + country_id;
      const response = await adminApi.get(url);
      if(response && response.data && response.data.length > 0)
      {
        setLeadList(response.data);
        setShowLeadList(true);
      }
    } catch (err) {
      handleError(err);
    }
  };

  const clearAll = () => {
    setLeadId(0);
    setCountryId(0);
    setClientId(0);
    setClientList([]);
    setMobileNo("");
    setEmailId("");
    setProductId(0);
    setLeadStatusId(0);
    setLeadSourceId(0);
    setExpectedBudget(0);
    setPriorityLevel("");
    clearAllTextFields();
    setFormHeading(ADD_LEAD);
    setShowLeadList(false);
    setIsCountryDisabled(false);
    setIsClientDisabled(false);
    setFocus("countriesCombo");
  };

  const validateBeforeSave = (): boolean => {
    if (countryId === 0) {
      showMessage(true,ERROR_MSG_TITLE,"Country selection required","countriesCombo");
      return false;
    }

    if (clientId=== 0) {
      showMessage(true,ERROR_MSG_TITLE,"Select Client","clientCombo");
      return false;
    }

    if (mobileNo.trim() === "") {
      showMessage(true,ERROR_MSG_TITLE,"Mobile No required","txtMobileNo");
      return false;
    }

    if (productId === 0) {
      showMessage(true,ERROR_MSG_TITLE,"Product selection required","productsCombo");
      return false;
    }

    if (leadStatusId === 0) {
      showMessage(true,ERROR_MSG_TITLE,"Lead Status selection required","leadStatusCombo");
      return false;
    }

    if (leadSourceId === 0) {
      showMessage(true,ERROR_MSG_TITLE,"Lead Source selection required","leadSourceCombo");
      return false;
    }
    if(priorityLevel.trim().length === 0)
    {
      showMessage(true,ERROR_MSG_TITLE,"Priority selection required","priorityCombo");
      return false;
    }
    return true;
  };

  const saveLeadRecord = async () => {
    if (!validateBeforeSave()) {
      return;
    }
    let lead_id=null;
    if(leadId > 0)
    {
      lead_id=leadId;
    }
    const leadData = {
      leadId: lead_id,
      client: { clientId: clientId  },
      interestedProduct: { productId: productId },
      leadStatus: { leadStatusId: leadStatusId },
      leadSource: { leadSourceId: leadSourceId },
      expectedBudget: Number(getInputValue("txtExpectedBudget", "number")),
      expectedClosureDate: getInputValue("txtExpectedClosureDate","string"),
      priorityLevel: priorityLevel,
      remarks: getInputValue("txtRemarks","string")
    };

    try {
      const url = getServerIP() + BIZ_CONNECT_API_PATH + "lead/update_lead";
      const response = await adminApi.post(url,leadData);
      const data: Lead = response.data;
      if (data.returnMessage.code === 0) 
      {
        showMessage(true,SAVE_MSG_TITLE,data.returnMessage.message,"");
        setLeadId(data.leadId);
        setFormHeading(EDIT_LEAD);
        displayLeadRecords(countryId);
        clearSingleTextField("txtRemarks");
      } else 
      {
        showMessage(true,ERROR_MSG_TITLE,data.returnMessage.message,"");
      }
    } catch (err) {
      handleError(err);
    }
  };

  const editLeadRecord = async (lead: Lead) => 
  {
    setFormHeading(EDIT_LEAD);
    setLeadId(lead.leadId);
    setClientId(lead.client.clientId);
    setMobileNo(lead.client.mobileNo);
    setEmailId(lead.client.email);
    setCountryId(lead.client.city.state.country.countryId);
    setProductId(lead.interestedProduct.productId);
    setLeadStatusId(lead.leadStatus.leadStatusId);
    setLeadSourceId(lead.leadSource.leadSourceId);
    setExpectedBudget(lead.expectedBudget);
    setPriorityLevel(lead.priorityLevel);
    setInputValue("txtExpectedBudget",lead.expectedBudget);
    setInputValue("txtExpectedClosureDate",lead.expectedClosureDate);
    setInputValue("txtRemarks",lead.remarks ?? "");
    setFocus("countriesCombo");
    setIsCountryDisabled(true);
    setIsClientDisabled(true);
  };

  const enableDeleteLead = (leadId: number) => 
  {
    setDeleteLeadId(leadId);
    setShowDialogBox(true);
  };

  const doDeleteLead = async () => 
  {
    try 
    {
      const url = getServerIP() + BIZ_CONNECT_API_PATH + "lead/delete_lead/" + deleteLeadId;
      const response = await adminApi.delete(url);
      const data = response.data;
      if (data.code === 0) 
      {
        showMessage(true,DELETE_MSG_TITLE,data.message,"");
        setShowDialogBox(false);
        displayLeadRecords(countryId);
        clearAll();
      } else 
      {
        showMessage(true,ERROR_MSG_TITLE,data.message,"");
      }
    } catch (err) {
      handleError(err);
    }
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error.message}</div>;
  }

  if (!open) {
    return null;
  }

  const country_change = async(country_id:number)=>
  {
    setCountryId(country_id);
    displayAllClientsByCountry(country_id);
    displayLeadRecords(country_id);
  }

  const displayAllClientsByCountry = async(country_id:number) => 
  {
    try 
    {
      const url = getServerIP() + BIZ_CONNECT_API_PATH + "client/get_all_clients_by_country/" + country_id;
      const response = await adminApi.get(url);
      if(response && response.data && response.data.length > 0)
      {
        setClientList(response.data);
      }
    } catch (err) {
      handleError(err);
    }
  }

  const client_change = async (client_id: number) => 
  {
    // Editing existing lead
    if (leadId > 0) {
        return;
    }

    setClientId(client_id);

    const selectedClient = clientList.find(client => client.clientId === client_id);

    if (selectedClient) 
    {
      setMobileNo(selectedClient.mobileNo);
      setEmailId(selectedClient.email);
    }
  };

  return (
    <FormTemplate width={"90%"} heading={formHeading} onClose={() => setOpen(false)}>
      <MessageBox open={showMessageBox} onClose={handleCloseMessageBox} title={title}>
        {displayMessage}
      </MessageBox>

      <ConfirmDialogBox title="Delete Lead" open={showDialogBox} onReject={() => setShowDialogBox(false)}
        setOpen={() => true} onConfirm={doDeleteLead}>
        Want to delete this lead ?
      </ConfirmDialogBox>

      <FormContent>
        <Box>
          <FormCell>
            <ReInputLabel labelValue="Country" />
          </FormCell>
          <FormCell>
            <ReSelect id="countriesCombo" value={countryId} options={countries}
              getOptionLabel={(opt: Country) => opt.countryName } getOptionValue={(opt: Country) => opt.countryId } 
              width="220px" onChange={(e) => country_change(Number(e.target.value)) }
              disabled={isCountryDisabled}
            />
          </FormCell>
          <FormCell>
            <ReInputLabel labelValue="Client" />
          </FormCell>
          <FormCell>
            <ReSelect id="clientsCombo" value={clientId} options={clientList} 
              getOptionLabel={(opt: Client) => opt.companyName } getOptionValue={(opt: Client) => opt.clientId } 
              width="250px" onChange={(e) => client_change(Number(e.target.value)) } disabled={isClientDisabled}
            />
          </FormCell>
          <FormCell>
            <ReInputLabel labelValue="Mobile No" />
          </FormCell>
          <FormCell>
            <ReTextField id="txtMobileNo" type="text" width="180px" value={mobileNo} disabled={true} />
          </FormCell>
        </Box>
        <Box>
          <FormCell>
            <ReInputLabel labelValue="Email" />
          </FormCell>
          <FormCell>
            <ReTextField id="txtEmailId" type="email" width="220px" value={emailId}  disabled={true} />
          </FormCell>
          <FormCell>
            <ReInputLabel labelValue="Product" />
          </FormCell>
          <FormCell>
            <ReSelect id="productsCombo" value={productId} options={products}
              getOptionLabel={(opt: Product) => opt.productName } getOptionValue={(opt: Product) => opt.productId   }
              width="250px" onChange={(e) => setProductId(Number(e.target.value))}
            />
          </FormCell>
          <FormCell>
            <ReInputLabel labelValue="Lead Status" />
          </FormCell>
          <FormCell>
            <ReSelect id="leadStatusCombo" value={leadStatusId} options={leadStatuses}
              getOptionLabel={(opt: LeadStatus) => opt.statusName}
              getOptionValue={(opt: LeadStatus) => opt.leadStatusId}
              width="220px" onChange={(e) =>setLeadStatusId(Number(e.target.value))}
            />
          </FormCell>
        </Box>
        <Box>
          <FormCell>
            <ReInputLabel labelValue="Lead Source" />
          </FormCell>
          <FormCell>
            <ReSelect id="leadSourceCombo" value={leadSourceId} options={leadSources}
              getOptionLabel={(opt: LeadSource) => opt.sourceName} 
              getOptionValue={(opt: LeadSource) => opt.leadSourceId} width="220px"
              onChange={(e) => setLeadSourceId(Number(e.target.value))}
            />
          </FormCell>
          <FormCell>
            <ReInputLabel labelValue="Expected Budget" />
          </FormCell>
          <FormCell>
            <ReTextField id="txtExpectedBudget" type="number" width="150px" value={expectedBudget}
              onChange={(e) => setExpectedBudget(Number(e.target.value))} />
          </FormCell>

          <FormCell>
            <ReInputLabel labelValue="Expected Closure Date" />
          </FormCell>

          <FormCell>
            <ReTextField id="txtExpectedClosureDate" type="date" width="180px"/>
          </FormCell>
        </Box>

        <Box>
          <FormCell>
            <ReInputLabel labelValue="Priority Level" />
          </FormCell>

          <FormCell>
            <ReSelect id="priorityCombo" value={priorityLevel} width="180px"
              options={[
                { value: "LOW", label: "LOW" },
                { value: "MEDIUM", label: "MEDIUM" },
                { value: "HIGH", label: "HIGH" },
              ]}
              getOptionLabel={(opt) => opt.label} getOptionValue={(opt) => opt.value}
              onChange={(e) => setPriorityLevel(e.target.value)}
            />
          </FormCell>

          <FormCell>
            <ReInputLabel labelValue="Remarks" />
          </FormCell>

          <FormCell colSpan={3}>
            <ReTextField id="txtRemarks" type="multiline" multiline rows={3} width="500px"/>
          </FormCell>
        </Box>

        <Box>
          <FormCell colSpan={6}>
            <Box display="flex" justifyContent="flex-end" gap={1} >
              <ReButton id="btnSave" buttonType="Save" label="Save" onClick={saveLeadRecord} />
              <ReButton id="btnClear" buttonType="Refresh" label="Clear" onClick={clearAll} />
            </Box>
          </FormCell>
        </Box>

        <Box>
          <FormCell colSpan={6}>
            <ListRecords showList={showLeadList} data={leadList}
              columns={[
                { label: "Client", valueAccessor: (l: Lead) =>l.client.companyName },
                { label: "Mobile", valueAccessor: (l: Lead) =>l.client.mobileNo },
                { label: "Product",valueAccessor: (l: Lead) =>l.interestedProduct.productName },
                { label: "Status", valueAccessor: (l: Lead) =>l.leadStatus.statusName },
                { label: "Budget", align: "right", valueAccessor: (l: Lead) => l.expectedBudget.toFixed(2) },
                { label: "Priority", valueAccessor: (l: Lead) => l.priorityLevel }
              ]}
              tableHeight="45vh"
              showEditColumn={true}
              showDeleteColumn={true}
              noRecordsMessage="No leads to display"
              onEdit={(lead: Lead) => editLeadRecord(lead) }
              onDelete={(lead: Lead) => enableDeleteLead(lead.leadId)}
            />
          </FormCell>
        </Box>

      </FormContent>
    </FormTemplate>
  );
};

export default LeadForm;