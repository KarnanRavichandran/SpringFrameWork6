import axios from 'axios'

export const apiClient = axios.create(
    {
         baseURL: 'http://Instincttodo-env.eba-njsrquwp.ap-south-1.elasticbeanstalk.com' 
    }
);

