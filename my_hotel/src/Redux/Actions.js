import {
    REQUEST_LOGIN_PENDING,
    REQUEST_LOGIN_SUCCESS,
    REQUEST_LOGIN_FAILED,
    REQUEST_LOGOUT,
    REQUEST_ROOMS_PENDING,
    REQUEST_ROOMS_SUCCESS,
    REQUEST_ROOMS_FAILED,
    REQUEST_ADDROOM_PENDING,
    REQUEST_ADDROOM_SUCCESS,
    REQUEST_ADDROOM_FAILED,
    REQUEST_DELETEROOM_PENDING,
    REQUEST_DELETEROOM_SUCCESS,
    REQUEST_DELETEROOM_FAILED,
    REQUEST_GETROOM_PENDING,
    REQUEST_GETROOM_SUCCESS,
    REQUEST_GETROOM_FAILED,
    UPDATE_TABLE_TRUE,
    UPDATE_TABLE_FALSE
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
        .then(handleServerError)
        .then(data => {
            dispatch({ type: REQUEST_LOGIN_SUCCESS, payload: data })
        })
        .catch(error => dispatch({ type: REQUEST_LOGIN_FAILED, payload: error }))
}

export const requestLogout = () => dispatch => {
    dispatch({ type: REQUEST_LOGOUT });
}

const handleServerError = (response) => {
    if (!response.ok) {
        throw Error("Resposnse failed!");
    }

    return response.json();
}

export const requestRooms = data => dispatch => {
    dispatch({ type: REQUEST_ROOMS_PENDING });
    fetch(api.rooms, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('User_Login_Token')
        }
    })
        .then(handleServerError)
        .then(data => {
            dispatch({ type: REQUEST_ROOMS_SUCCESS, payload: data })
        })
        .catch(error => dispatch({ type: REQUEST_ROOMS_FAILED, payload: error }))
}

export const requestAddRoom = (data, options) => dispatch => {
    dispatch({ type: REQUEST_ADDROOM_PENDING });
    const currentFacilities = [];
    Object.keys(options.map((item, i) => currentFacilities.push(item.name)))

    fetch(api.rooms, {
        method: 'POST',
        body: JSON.stringify({
            "number": data.roomNumber,
            "beds": data.beds,
            "facilities": {
                "wifi": currentFacilities.indexOf("WIFI") > -1,
                "ac": currentFacilities.indexOf("AC") > -1,
                "tv": currentFacilities.indexOf("TV") > -1,
                "nsr": currentFacilities.indexOf("NSR") > -1
            },
            "reserved": false,
            "price": data.price
        }),
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('User_Login_Token')
        }
    })
        .then(data => {
            dispatch({ type: REQUEST_ADDROOM_SUCCESS, payload: data })
            dispatch({ type: UPDATE_TABLE_TRUE })
        })
        .catch(error => dispatch({ type: REQUEST_ADDROOM_FAILED, payload: error }))
    dispatch({ type: UPDATE_TABLE_FALSE })
}

export const requestDeleteRoom = (index) => dispatch => {
    dispatch({ type: REQUEST_DELETEROOM_PENDING })
    fetch(api.rooms + `/${index}`, {
        method: 'DELETE'
    })
        .then(resp => {
            dispatch({ type: REQUEST_DELETEROOM_SUCCESS, payload: resp })
            dispatch({ type: UPDATE_TABLE_TRUE })
        })
        .catch(error => dispatch({ type: REQUEST_DELETEROOM_FAILED, payload: error }))
    dispatch({ type: UPDATE_TABLE_FALSE })
}

export const requestGetRoom = index => dispatch => {
    dispatch({ type: REQUEST_GETROOM_PENDING })
    fetch(api.rooms + `/${index}`, {
        method: 'GET'
    })
        .then(resp => resp.json())
        .then(data => {
            dispatch({ type: REQUEST_GETROOM_SUCCESS, payload: data })
        })
        .then(error => dispatch({ type: REQUEST_GETROOM_FAILED, payload: error }))
}

