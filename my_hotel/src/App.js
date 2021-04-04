import React from 'react'
import LoginPage from './LoginPage/LoginPage'
import { requestLogin } from './Redux/Actions'
import { connect } from 'react-redux'
import { BrowserRouter as Router, Route } from 'react-router-dom'

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
    <Router>
      <div className="App">
        <Route path='/login' render={() => { return (<LoginPage store={props} />) }} />
      </div>
    </Router>
  );
}

export default connect(mapStateToProps, mapDispatchToProps)(App);
