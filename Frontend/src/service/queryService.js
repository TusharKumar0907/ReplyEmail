import axios from 'axios';

// const URL = 'http://localhost:8080/api/email-reply';
// const URL = process.env.REACT_APP_API_URL;
const URL = import.meta.env.VITE_BACKEND_URL;
// console.log(URL);


export const submitQuery = (body) => {
    return axios.post(URL, body);
}