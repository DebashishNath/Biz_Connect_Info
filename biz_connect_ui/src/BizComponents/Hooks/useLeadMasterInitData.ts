import { useEffect, useState } from "react";
import { Country, Product , LeadStatus, LeadSource } from "../Interfaces/bizConnectInterfaces";
import { getServerIP, useAdminApi } from "@kcndigitals/lib";
import { BIZ_CONNECT_API_PATH } from "../Constants/BizConnectConstants";

export function useLeadMasterInitData() {
  const [data, setData]       = useState<{
    countries: Country[];
    products: Product[];
    leadStatuses: LeadStatus[];
    leadSources: LeadSource[];
  } | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError]     = useState<Error | null>(null);
  const adminApi = useAdminApi();
  
  useEffect(() => {
    adminApi(`${getServerIP()}` + BIZ_CONNECT_API_PATH + 'lead/lead_master_data')
      .then(res => setData(res.data))
      .catch(setError)
      .finally(() => setLoading(false));
  }, [adminApi]);

  return {
    countries: data?.countries ?? [],
    products: data?.products ?? [],
    leadStatuses: data?.leadStatuses ?? [],
    leadSources: data?.leadSources ?? [],

    loading,
    error
  };
}