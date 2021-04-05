import React from 'react'
import { requestLogin } from './Redux/Actions'
import { connect } from 'react-redux'
import Main from './Main'

const mapStateToProps = state => {
  return {
    requestLogin: state.requestLogin
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onRequestLogin: (data) => requestLogin(data)
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
