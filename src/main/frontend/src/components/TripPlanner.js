import React, {useEffect, useState} from 'react'
import TripTable from './TripTable'
import AddStop from "./AddStop";
import {Grid, Paper} from "@material-ui/core";

const tripData =
  {
    stops: [
      {
        id: "1",
        cityName: "Adelaide",
        countryCode: "AU",
        arriveTs: "2020-01-01",
        departTs: "2020-01-02",
        forecasts: [
          {
            id: "1",
            forTs: "2020-01-02 09:00:00",
            temperature: 21,
            weatherMain: "Rain"
          },
          {
            id: "2",
            forTs: "2020-01-02 21:00:00",
            temperature: 24,
            weatherMain: "Cloudy"
          }
        ]
      },
      {
        id: "2",
        cityName: "Perth",
        countryCode: "AU",
        arriveTs: "2020-01-02",
        departTs: "2020-01-03",
      }
    ]
  };

const TripPlanner = () => {
  const [trip, setTrip] = useState(tripData)

  // add a new stop to the trip state
  const addStop = (stop) => {
    stop.loadForecasts = true;
    setTrip(trip.stops ? {...trip, stops: [...trip.stops, stop]} : {...trip, stops: [stop]})
  }

  // when "stops" changes, find any stop that needs its forecasts loaded, and load em...
  useEffect(
    () => {
      trip.stops
        .filter((stop) => stop.loadForecasts)
        .forEach((stop) => {
            loadForecasts(stop.cityName)
          }
        );
    },
    [trip.stops]
  );

  // load forecasts from remote API
  const loadForecasts = (cityName) => {
    async function fetchForecasts() {
      if (cityName === "Renmark") {
        addForecasts("Renmark", [
          {
            "cityName": "Renmark",
            "countryCode": "AU",
            "timestamp": "2021-07-27T18:00:00.000+00:00",
            "temperature": 14,
            "weatherMain": "Rain"
          },
          {
            "cityName": "Renmark",
            "countryCode": "AU",
            "timestamp": "2021-07-27T21:00:00.000+00:00",
            "temperature": 13,
            "weatherMain": "Rain"
          }
        ]);
        return;
      }

      const response = await fetch("http://localhost:8080/api/weather/cityForecast/" + cityName);
      const json = await response.json();
      addForecasts(cityName, json.data);
    }

    fetchForecasts();
  }

  // add new forecasts to the trip state
  const addForecasts = (cityName, forecasts) => {
    trip.stops
      .forEach((stop, i) => {
          if (stop.loadForecasts && stop.cityName === cityName) {
            const newTrip = {...trip};
            newTrip.stops[i].forecasts = forecasts;
            setTrip(newTrip);
          }
        }
      );
  }

  return (
    <Grid container spacing={2}>
      <Grid item xs={12}>
        <Paper style={{padding: "10px"}}>
          <AddStop addStop={addStop}/>
        </Paper>
      </Grid>
      <Grid item xs={12}>
        <Paper style={{padding: "10px"}}>
          <h3>Your Trip</h3>
          <TripTable trip={trip}/>
        </Paper>
      </Grid>
    </Grid>
  )
}

export default TripPlanner
