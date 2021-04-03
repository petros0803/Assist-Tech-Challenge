import React from 'react'
import LoginPage from './LoginPage/LoginPage'
import { requestLogin } from './Redux/Actions'
import { connect } from 'react-redux'

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
      <LoginPage store={props} />
    </div>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(App);
