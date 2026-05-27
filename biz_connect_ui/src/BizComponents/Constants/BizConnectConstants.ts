import { Month } from "../Interfaces/bizConnectInterfaces";

export const BIZ_CONNECT_API_PATH = 'api/biz_connect/';
export const ADD_NEW_CLIENT = 'Add New Client';
export const EDIT_CLIENT = 'Edit Client';
export const ADD_NEW_COUNTRY = 'Add New Country';
export const EDIT_COUNTRY = 'Edit Country';
export const ADD_NEW_STATE = 'Add New State';
export const EDIT_STATE = 'Edit State';
export const ADD_NEW_CITY = 'Add New City';
export const EDIT_CITY = 'Edit City';

export const MONTHS: Month[] = [
    { monthId: 1, monthName: "January" },
    { monthId: 2, monthName: "February" },
    { monthId: 3, monthName: "March" },
    { monthId: 4, monthName: "April" },
    { monthId: 5, monthName: "May" },
    { monthId: 6, monthName: "June" },
    { monthId: 7, monthName: "July" },
    { monthId: 8, monthName: "August" },
    { monthId: 9, monthName: "September" },
    { monthId: 10, monthName: "October" },
    { monthId: 11, monthName: "November" },
    { monthId: 12, monthName: "December" },
];

export const clientTypes = [
    { id: 'INDIVIDUAL', value: 'INDIVIDUAL' },
    { id: 'COMPANY', value: 'COMPANY' }
  ];