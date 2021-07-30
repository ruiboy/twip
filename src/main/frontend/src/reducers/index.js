import {combineReducers} from "redux";
import tripReducer from "./tripRecucer";

export default combineReducers({
  trip: tripReducer
});
