import { combineReducers } from 'redux'
import {
  requestLoginReducer,
  requestRoomsReducer,
  requestAddRoomReducer,
  requestDeleteRoomReducer,
  requestGetRoomReducer,
  updateTableReducer,
} from './Reducers';

const allReducers = combineReducers({
  requestLogin: requestLoginReducer,
  requestRooms: requestRoomsReducer,
  requestAddRoom: requestAddRoomReducer,
  requestDeleteRoom: requestDeleteRoomReducer,
  requestGetRoom: requestGetRoomReducer,
  updateTable: updateTableReducer,
})

export default allReducers;
