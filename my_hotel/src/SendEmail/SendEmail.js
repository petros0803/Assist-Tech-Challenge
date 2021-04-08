import React, { useState } from 'react'
import './sendemail.css'
import { useHistory } from "react-router-dom";
import api from '../Api-connections/Api-connections'

const SendEmail = () => {
    const [subject, setSubject] = useState()
    const [body, setBody] = useState()
    const [errors, setErrors] = useState([])
    let history = useHistory();

    const checkDataBeforeSend = () => {
        let error = []
        let re = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/

        if (subject < 0 || subject === undefined || subject.length === 0) {
            error.push("Subject is required!")
        }
        if (body < 0 || body === undefined || body.length === 0) {
            error.push("Body is required!")
        }

        setErrors(error)

        if (error.length > 0) {
            return false;
        }

        return true;
    }

    const sendPromotionalEmail = () => {
        if (checkDataBeforeSend()) {
            fetch(api.sendEmail, {
                method: 'POST',
                body: JSON.stringify({
                    "subject": subject,
                    "body": body
                }),
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('User_Login_Token')
                }
            })

            alert("A promotional email has been sent!")
            history.push("/admin/rooms")
        }
    }

    return (
        <div>
            <form className="form__sendEmailContainer">
                <h1 className="form_sendEmailTitle"> Send </h1>
                <h1 className="form_sendEmailTitle"> promotional email </h1>
                <div className="form__sendEmailInputs">
                    <p className="form__p"> Email subject </p>
                    <input type="text" className="form_SendEmailInputs" name="emailSubject" onChange={event => setSubject(event.target.value)} />

                    <p className="form__p"> Email body </p>

                    <textarea className="form_SendEmailInputs" name="emailBody" onChange={event => setBody(event.target.value)} />

                    <button className="form__sendEmailRoomButton" type="button" onClick={() => sendPromotionalEmail()} > Send</button>
                    {errors ? <div className="form_SendEmailErrorsDiv"> {errors.map((index, i) => <p key={i} className="from__pSendEmailErrors"> {index} </p>)} </div> : ''}
                </div>
            </form>
        </div>
    )

}

export default SendEmail