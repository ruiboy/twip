import React from 'react'
import TripTable from './TripTable'
import AddStop from "./AddStop";
import {Grid, Paper} from "@material-ui/core";
import TripManager from "./TripManager";


const TripPlanner = () => {
  return (
    <Grid container spacing={2}>
      <Grid item xs={12}>
        <Paper style={{padding: "10px"}}>
          <h3>Add a Stop</h3>
          <AddStop/>
        </Paper>
      </Grid>
      <Grid item xs={12}>
        <Paper style={{padding: "10px"}}>
          <h3>Your Trip</h3>
          <TripTable/>
        </Paper>
      </Grid>
      <Grid item xs={12}>
        <Paper style={{padding: "10px"}}>
          <h3>Trip Management</h3>
          <TripManager/>
        </Paper>
      </Grid>
    </Grid>
  )
}

export default TripPlanner
