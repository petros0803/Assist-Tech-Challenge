import {
    REQUEST_LOGIN_PENDING,
    REQUEST_LOGIN_SUCCESS,
    REQUEST_LOGIN_FAILED
} from './Constants';
import api from '../Api-connections/Api-connections';

export const requestLogin = data => dispatch => {
    dispatch({ type: REQUEST_LOGIN_PENDING });
    fetch(api.authentificate, {
        method: 'POST',
        body: JSON.stringify({
            "email": data.email,
            "password": data.password
        }),
        headers: { 'Content-Type': 'application/json' }
    })
        .then(resp => resp.json())
        .then(data => {
            dispatch({ type: REQUEST_LOGIN_SUCCESS, payload: data })
        })
        .catch(error => dispatch({ type: REQUEST_LOGIN_FAILED, payload: error }))
}