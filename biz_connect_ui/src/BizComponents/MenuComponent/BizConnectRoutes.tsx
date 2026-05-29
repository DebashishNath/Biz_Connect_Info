import React from 'react';
import { Navigate, useRoutes } from 'react-router-dom';

import {SignupForm,ForgotPassword,ResetPassword,AutoLogin,ProtectedRoute, Login, LoginTag, OtpVerificationScreen}  from '@kcndigitals/lib';
import MainMenuForm from './MainMenuForm';

/* children under /mainmenu */
import BusinessConnectDashboardForm           from '../DashboardComponent/BusinessConnectDashboardForm';
import ClientForm from '../MasterComponent/ClientForm';
import CountryForm from '../MasterComponent/CountryForm';
import StateForm from '../MasterComponent/StateForm';
import CityForm from '../MasterComponent/CityForm';
import LeadForm from '../TransactionComponent/LeadForm';

interface BizConnectRoutesProps {
  isAutoLoginComplete: boolean;
  onAutoLoginDone: () => void;   // 👈 add callback prop
}

const BizConnectRoutes: React.FC<BizConnectRoutesProps> = ({
  isAutoLoginComplete,
  onAutoLoginDone,
}) => {
  const element = useRoutes([
    {
      path: '/',
      element: <Login tag={LoginTag.BIZ_Connect} />,
    },
    {
      path: '/signup',
      element: <SignupForm />,
    },
    {
      path: '/forgot-password',
      element: <ForgotPassword />,
    },
    {
      path: '/reset-password',
      element: <ResetPassword />,
    },
    {
      path: '/verifyOTP',
      element: <OtpVerificationScreen />,
    },
    {
      /* Auto-login gate */
      path: '/',
      element: !isAutoLoginComplete ? (
        <AutoLogin onComplete={onAutoLoginDone}  />
      ) : (
        <Navigate to="/mainmenu" replace />
      ),
    },
    {
      path: '/mainmenu',
      element: (
        <ProtectedRoute>
          <MainMenuForm />
        </ProtectedRoute>
      ),
      children: [
        { index: true, element: <Navigate to="business-connect-dashboard" replace /> },
        { path: 'business-connect-dashboard', element: <BusinessConnectDashboardForm /> },
        { path: 'country', element: <CountryForm /> },
        { path: 'state', element: <StateForm /> },
        { path: 'city', element: <CityForm /> },
        { path: 'client', element: <ClientForm /> },
        { path: 'lead', element: <LeadForm /> }
      ],
    },
    /* catch-all */
    { path: '*', element: <Navigate to="/" replace /> },
  ]);

  return element;
};

export default BizConnectRoutes;