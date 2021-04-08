import React from 'react'
import {
  requestLogin,
  requestLogout,
  requestRooms,
  requestAddRoom,
  requestDeleteRoom,
  requestGetRoom,
} from './Redux/Actions'
import { connect } from 'react-redux'
import Main from './Main'

const mapStateToProps = state => {
  return {
    requestLogin: state.requestLogin,
    requestRooms: state.requestRooms,
    requestAddRoom: state.requestAddRoom,
    requestDeleteRoom: state.requestDeleteRoom,
    requestGetRoom: state.requestGetRoom,
    updateTable: state.updateTable
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onRequestLogin: (data) => requestLogin(data),
    onRequestLogout: () => requestLogout(),
    onRequestRooms: () => requestRooms(),
    onRequestAddRoom: (data, options) => requestAddRoom(data, options),
    onRequestDeleteRoom: (index) => requestDeleteRoom(index),
    onRequestGetRoom: (index) => requestGetRoom(index),
  }
}

function App(props) {
  return (
    <div className="App">
      <Main store={props} />
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(App);
