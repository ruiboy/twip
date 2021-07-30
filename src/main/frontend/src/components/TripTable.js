import React from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from '@material-ui/core';
import {useSelector} from "react-redux";

function Forecast(props) {
  const forecasts = props.forecasts;

  return (
    <TableContainer component={Paper}>
      <Table size="small" aria-label="weather table">
        <TableHead>
          <TableRow>
            <TableCell>Time</TableCell>
            <TableCell>Temp</TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {forecasts.map((forecast) => (
            <TableRow key={forecast.id}>
              <TableCell>{new Date(forecast.forTs).toLocaleString()}</TableCell>
              <TableCell>{forecast.temperature}</TableCell>
              <TableCell>{forecast.weatherMain}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

const TripTable = () => {
  const trip = useSelector(state => state.trip);

  return (
    <TableContainer>
      <Table size="small" aria-label="trip table">
        <TableHead>
          <TableRow style={{backgroundColor: "beige"}}>
            <TableCell>City</TableCell>
            <TableCell>Country</TableCell>
            <TableCell>Arrival Date</TableCell>
            <TableCell>Departure Date</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {trip && trip.stops && trip.stops.map((row) => (
            <>
              <TableRow key={row.id}>
                <TableCell>{row.cityName}</TableCell>
                <TableCell>{row.countryCode}</TableCell>
                <TableCell>{row.arriveTs}</TableCell>
                <TableCell>{row.departTs}</TableCell>
              </TableRow>
              {row.forecasts &&
              <TableRow key={row.id + "_forecasts"}>
                <TableCell>Weather Forecast for {row.cityName}</TableCell>
                <TableCell colSpan={3}>
                  <Forecast forecasts={row.forecasts}/>
                </TableCell>
              </TableRow>
              }
            </>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default TripTable
