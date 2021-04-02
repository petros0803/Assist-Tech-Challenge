import React, { Component } from 'react'
import { useForm } from 'react-hook-form'
import './loginpage.css'

const LoginPage = () => {
    const { register, handleSubmit, formState: { errors }, watch } = useForm();

    const onSubmit = (data) => {
        console.log(data)
    }

    return (
        <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input type="text" palceholder="email" name="email" {...register('email', {
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

                <input type="password" name="password" placeholder="password" {...register('password', {
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

                <input type="submit" />
            </form>
        </div>
    )
}

export default LoginPage
