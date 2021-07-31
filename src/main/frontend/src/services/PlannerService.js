import http from './HttpUtil'

const loadTrip = (name) => {
  return http.get("/api/planner/trip/" + name);
};

const saveTrip = (name, trip) => {
  return http.put("/api/planner/trip/" + name, trip);
};

const PlannerService = {
  loadTrip,
  saveTrip
};

export default PlannerService;
