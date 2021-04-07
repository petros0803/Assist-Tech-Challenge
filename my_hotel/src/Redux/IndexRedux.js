import { combineReducers } from 'redux'
import {
  requestLoginReducer,
  requestRoomsReducer,
  requestAddRoomReducer,
  requestDeleteRoomReducer,
  requestGetRoomReducer
} from './Reducers';

const allReducers = combineReducers({
  requestLogin: requestLoginReducer,
  requestRooms: requestRoomsReducer,
  requestAddRoom: requestAddRoomReducer,
  requestDeleteRoom: requestDeleteRoomReducer,
  requestGetRoom: requestGetRoomReducer
})

export default allReducers;
