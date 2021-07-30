import {ADD_STOPS} from "./types";
import WeatherService from "../services/WeatherService";

// will return null if can't find city, or error occurs
const getForecasts = async (cityName) => {
  try {
    const results = await WeatherService.getCityForecast(cityName);
    return results.data;
  } catch (err) {
    return;
  }
}

/*
  Convert the list of forecasts in to a nested structure like this; there may be multiple cities.

  {
    cityName: "Adelaide",
    countryCode: "AU",
    arriveTs: "2020-01-01",
    departTs: "2020-01-02",
    forecasts: [
      {
        forTs: 1627970400000,
        temperature: 21,
        weatherMain: "Rain"
      },
      {
        forTs: 1627970400000",
        temperature: 24,
        weatherMain: "Cloudy"
      }
    ]
  }
*/
// will return empty array if can't find forecasts in the date range
const buildStops = (cityName, arriveTs, departTs, forecasts) => {
  // find forecast data between start of first day and end of last day; only want weather for 12pm -> 6pm
  const start = new Date(arriveTs)
  start.setHours(0);
  const end = new Date(departTs)
  end.setHours(23)

  const stops = new Map();

  forecasts.forEach((value) => {
      let ts = new Date(value.timestamp);
      if (ts > start && ts < end && ts.getHours() >= 12 && ts.getHours() <= 18) {
        let stop = stops.get(value.countryCode);
        if (!stop) {
          stop = {
            cityName: value.cityName,
            countryCode: value.countryCode,
            arriveTs: arriveTs,
            departTs: departTs,
            forecasts: []
          }
          stops.set(stop.countryCode, stop);
        }
        stop.forecasts.push(
          {
            forTs: value.timestamp,
            temperature: value.temperature,
            weatherMain: value.weatherMain
          }
        );
      }
    }
  );

  return Array.from(stops.values());
}

export const addStop = (cityName, arriveTs, departTs) => async (dispatch) => {
  const forecasts = await getForecasts(cityName);
  if (!forecasts) {
    alert("Could not find forecasts for city " + cityName);
    return;
  }

  const stops = buildStops(cityName, arriveTs, departTs, forecasts);
  if (stops.length === 0) {
    alert("Could not find forecasts on the dates of your travel.");
    return;
  }

  try {
    dispatch({
      type: ADD_STOPS,
      payload: stops,
    });
  } catch (err) {
    console.log(err);
  }
};
