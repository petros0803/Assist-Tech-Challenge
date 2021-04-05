import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Redirect } from 'react-router';
import LoginPage from './LoginPage/LoginPage'

const Main = (props) => {
    return (
        <Router>
            <div className="main">
                <Route path='/login' render={() => !localStorage.getItem('User_Login_Token') ? <LoginPage store={props.store} /> : <Redirect to='/' />} />
            </div>
        </Router>
    )
}

export default Main