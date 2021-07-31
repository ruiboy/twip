import http from './HttpUtil'

const getCityForecast = (cityName) => {
  return http.get("/api/weather/cityForecast/" + cityName);
};

const WeatherService = {
  getCityForecast
};

export default WeatherService;
