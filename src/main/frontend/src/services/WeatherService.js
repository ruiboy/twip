import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json"
  }
});

const getCityForecast = (cityName) => {
  return http.get("/api/weather/cityForecast/" + cityName);
};

const WeatherService = {
  getCityForecast
};

export default WeatherService;
