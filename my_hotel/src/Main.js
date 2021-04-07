import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Redirect } from 'react-router';
import LoginPage from './LoginPage/LoginPage'
import Rooms from './Rooms/Rooms'
import Navbar from './Navbar/Navbar'
import AddEditRoom from './Rooms/AddEditRoom'

const Main = (props) => {
    return (
        <Router>
            <div className="main">
                {localStorage.getItem('User_Login_Token') ? <Navbar store={props.store} /> : ''}
                <Route path='/' exact render={() => <Redirect to='/admin/rooms' />} />
                <Route path='/login' render={() => !localStorage.getItem('User_Login_Token') ? <LoginPage store={props.store} /> : <Redirect to='/admin/rooms' />} />
                <Route path='/admin/rooms' exact render={() => localStorage.getItem('User_Login_Token') ? <Rooms store={props.store} /> : <Redirect to='/login' />} />
                <Route path='/admin/rooms/add' exact render={() => localStorage.getItem('User_Login_Token') ? <AddEditRoom store={props.store} addRoom={true} /> : <Redirect to='/login' />} />
                <Route path='/admin/rooms/edit/:id' render={() => localStorage.getItem('User_Login_Token') ? <AddEditRoom store={props.store} addRoom={false} /> : <Redirect to='/login' />} />
            </div>
        </Router>
    )
}

export default Main