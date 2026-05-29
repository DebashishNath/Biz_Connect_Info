import { useEffect, useState, useCallback } from 'react';
import { Country} from '../Interfaces/bizConnectInterfaces';
import { getServerIP, useAdminApi } from '@kcndigitals/lib';
import { BIZ_CONNECT_API_PATH } from '../Constants/BizConnectConstants';

// Generic fetch hook
function useFetchData<T>(endpoint: string) {
  const [data, setData] = useState<T[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<unknown>(null);
  const adminApi = useAdminApi();

  const fetchData = useCallback(async () => {
    try {
      const response = await adminApi(`${getServerIP()}${endpoint}`);
      setData(response.data);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  }, [adminApi, endpoint]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  return { data, loading, error };
}

// Hook to fetch countries
export function useCountries() {
  const { data, loading, error } = useFetchData<Country>(BIZ_CONNECT_API_PATH + 'country/countries_list');
  return { countries: data, countriesLoading: loading, countriesError: error };
}