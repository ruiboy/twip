import React, {useState} from "react";
import {Button, Grid, TextField} from "@material-ui/core";
import {useDispatch, useSelector} from "react-redux";
import {loadTrip, newTrip, saveTrip} from "../actions/tripActions";

// form for loading and storing trips

const TripManager = (props) => {
  const tripState = useSelector(state => state.trip);

  const initialFormState = {name: ""}
  const [formState, setFormState] = useState(initialFormState)

  const dispatch = useDispatch();

  const handleInputChange = (event) => {
    const {name, value} = event.target
    setFormState({...formState, [name]: value})
  }

  const saveTripHelper = () => {
    if (!formState.name) {
      alert("Please enter trip name to save.")
      return
    }
    dispatch(saveTrip(formState.name, tripState))
  }

  const loadTripHelper = () => {
    if (!formState.name) {
      alert("Please enter trip name to load.")
      return
    }
    dispatch(loadTrip(formState.name))
  }

  const newTripHelper = () => {
    dispatch(newTrip())
    setFormState(initialFormState);
  }

  return (
    <form>
      <Grid container spacing={2} alignItems="flex-end">
        <Grid item>
          <TextField
            type="text"
            name="name"
            value={formState.name}
            label="Trip Name"
            placeholder="Name"
            InputLabelProps={{
              shrink: true,
            }}
            required
            onChange={handleInputChange}
          />
        </Grid>
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            onClick={saveTripHelper}>
            Save this Trip
          </Button>
        </Grid>
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            onClick={loadTripHelper}>
            Load a Trip
          </Button>
        </Grid>
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            onClick={newTripHelper}>
            Start a new Trip
          </Button>
        </Grid>
      </Grid>
    </form>
  );
}

export default TripManager
