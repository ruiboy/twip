import {ADD_STOPS, NEW_TRIP} from "../actions/types";

const initialState =
  {
    // stops: [
    //   {
    //     id: "1",
    //     cityName: "Adelaide",
    //     countryCode: "AU",
    //     arriveTs: "2020-01-01",
    //     departTs: "2020-01-02",
    //     forecasts: [
    //       {
    //         id: "1",
    //         forTs: "2020-01-02 09:00:00",
    //         temperature: 21,
    //         weatherMain: "Rain"
    //       },
    //       {
    //         id: "2",
    //         forTs: "2020-01-02 21:00:00",
    //         temperature: 24,
    //         weatherMain: "Cloudy"
    //       }
    //     ]
    //   }
    // ]
  };

function tripReducer(trip = initialState, action) {
  const {type, payload} = action;

  switch (type) {
    case ADD_STOPS:
      const stops = payload;
      return (trip.stops ? {...trip, stops: [...trip.stops, ...stops]} : {...trip, stops: [...stops]})

    case NEW_TRIP:
      const newTrip = payload;
      return (newTrip)

    default:
      return trip;
  }
};

export default tripReducer;
