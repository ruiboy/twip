import React, {useState} from "react";
import {Button, Grid, TextField} from "@material-ui/core";

const AddStop = (props) => {
  const initialFormState = {cityName: '', arriveTs: ''}
  const [stop, setStop] = useState(initialFormState)

  const handleInputChange = (event) => {
    const {name, value} = event.target
    setStop({...stop, [name]: value})
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
          <Button
            variant="contained"
            color="primary"
            onClick={event => {
              event.preventDefault()
              if (!stop.cityName || !stop.arriveTs) {
                alert("Data missing!")
                return
              }
              props.addStop(stop)
              setStop(initialFormState)
            }}>
            Add City
          </Button>
        </Grid>
      </Grid>
    </form>
  );
}

export default AddStop
