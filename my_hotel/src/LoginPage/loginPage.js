import React, { Component } from 'react'
import { useForm } from 'react-hook-form'
import './loginpage.css'
import api from '../Api-connections/Api-connections';

const LoginPage = () => {
    const { register, handleSubmit, formState: { errors }, watch } = useForm();

    const onSubmit = data => {
        fetch(api.authentificate, {
            method: 'POST',
            body: JSON.stringify({data}),
            headers: {'Content-Type': 'application/json'}
        })
        .then(resp => resp.json())
        .then(response => console.log(response))
    }

    return (
        <div>
            <form className="form-container" onSubmit={handleSubmit(onSubmit)}>
                <h1 className="form__logintitle">Login</h1>
                <div className="form__inputs">
                    <input type="text" placeholder="Email" name="email" {...register('email', {
                        required: {
                            value: true,
                            message: "Email is required!"
                        },
                        pattern: {
                            value: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/,
                            message: "Email is invalid!"
                        }
                    })} />

                    {errors.email && (<span> {errors.email.message} </span>)}

                    <input type="password" name="password" placeholder="Password" {...register('password', {
                        required: {
                            value: true,
                            message: "Password is required!"
                        },
                        minLength: {
                            value: 6,
                            message: "Password must be at least 6 characters long!"
                        }
                    })} />

                    {errors.password && (<span> {errors.password.message} </span>)}
                </div>
                <input type="submit" className="form__loginbutton" value="Login"/>
            </form>
        </div>
    )
}

export default LoginPage
