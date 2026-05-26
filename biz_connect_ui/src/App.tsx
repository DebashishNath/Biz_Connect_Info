import React, { useState } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider, LoginTag, setLoginTag } from '@kcndigitals/lib';
import BizConnectRoutes from './BizComponents/MenuComponent/BizConnectRoutes';

const App: React.FC = () => {
  const [autoDone, setAutoDone] = useState(false);
  setLoginTag(LoginTag.BIZ_Connect);
  
  return (
    <AuthProvider>
      <BrowserRouter>
        <BizConnectRoutes isAutoLoginComplete={autoDone} onAutoLoginDone={() => setAutoDone(true)} />
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;