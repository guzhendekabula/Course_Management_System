import axios from "axios";

const MyAxios = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 5000,
    withCredentials: true
})

MyAxios.interceptors.response.use(
    function (response) {
        console.log(response);
        return response;
    },
    function (error) {
        console.log(error);
        return Promise.reject(error);
    }
)

export default MyAxios;