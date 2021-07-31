import React, {useState} from "react";
import {Button, Grid, TextField} from "@material-ui/core";
import {useDispatch} from "react-redux";
import {addStop} from "../actions/tripActions";

// form for adding a stop to a trip

const AddStop = (props) => {
  const initialFormState = {cityName: "", arriveTs: "", departTs: ""}
  const [stop, setStop] = useState(initialFormState)

  const dispatch = useDispatch();

  const handleInputChange = (event) => {
    const {name, value} = event.target
    setStop({...stop, [name]: value})
  }

  const submit = () => {
    if (!stop.cityName || !stop.arriveTs) {
      alert("Please enter city and arrival date at minimum.")
      return
    } else if (new Date(stop.arriveTs) <= new Date()) {
      alert("Your trip can't start before tomorrow.");
      return
    } else if (new Date(stop.departTs) < new Date(stop.arriveTs)) {
      alert("You can't depart before you arrive.");
      return
    } else if (!stop.departTs) {
      stop.departTs = stop.arriveTs;
    }
    dispatch(addStop(stop.cityName, stop.arriveTs, stop.departTs))
    setStop(initialFormState)
  }

  return (
    <form>
      <Grid container spacing={2} alignItems="flex-end">
        <Grid item>
          <TextField
            type="text"
            name="cityName"
            value={stop.cityName}
            label="City"
            placeholder="City"
            InputLabelProps={{
              shrink: true,
            }}
            required
            onChange={handleInputChange}
          />
        </Grid>
        <Grid item>
          <TextField
            type="date"
            name="arriveTs"
            value={stop.arriveTs}
            label="Arrival Date"
            InputLabelProps={{
              shrink: true,
            }}
            required
            onChange={handleInputChange}
          />
        </Grid>
        <Grid item>
          <TextField
            type="date"
            name="departTs"
            value={stop.departTs}
            label="Depart Date"
            InputLabelProps={{
              shrink: true,
            }}
            onChange={handleInputChange}
          />
        </Grid>
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            onClick={submit}>
            Add City
          </Button>
        </Grid>
      </Grid>
    </form>
  );
}

export default AddStop
