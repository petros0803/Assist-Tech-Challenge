import React, { useState } from 'react'
import './loginPage.css'

class LoginPage extends React.Component {
    constructor(){
        super();
        this.state = {
          email: '',
          password: '',
          errors: []
        }
    }

    handleChangeEmail(email) {
		this.setState({ email: email });
    }

    handleChangePassword(password) {
		this.setState({ password: password });
    }

    

    isEmailValid = () => {
        const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

        return re.test(this.state.email);
    }

    sendLoginData = () => {
        

    }

    render() {
        return(
            <div>
                <input type="text" placeholder="email" value = {this.state.email}  onChange = {e => this.handleChangeEmail(e.target.value)} />
                <input type="text" placeholder="password" value = {this.state.password} onChange = {e => this.handleChangePassword(e.target.value)} />
                <button value = "Send Login data" onClick = {this.sendLoginData}/>
            </div>
        )
    }
}

export default LoginPage;