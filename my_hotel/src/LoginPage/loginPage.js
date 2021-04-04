import React from 'react'
import { useForm } from 'react-hook-form'
import './loginpage.css'
import { useDispatch } from 'react-redux'

const LoginPage = (props) => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const dispatch = useDispatch();

    const onSubmit = data => {
        dispatch(props.store.onRequestLogin(data))
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
                <input type="submit" className="form__loginbutton" value="Login" />
            </form>
        </div>
    )
}

export default LoginPage
