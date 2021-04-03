import { combineReducers } from 'redux'
import { requestLoginReducer } from './Reducers';

const allReducers = combineReducers({
  requestLogin: requestLoginReducer
})

export default allReducers;
