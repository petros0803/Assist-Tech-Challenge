import {
    REQUEST_LOGIN_PENDING,
    REQUEST_LOGIN_SUCCESS,
    REQUEST_LOGIN_FAILED
} from './Constants'

const initialStateLogin = {
    isLoggedin: false,
    loginToken: [],
    error: ''
}

export const requestLoginReducer = (state = initialStateLogin, action = {}) => {
    switch (action.type) {
        case REQUEST_LOGIN_PENDING:
            return Object.assign({}, state, { isLoggedin: true })
        case REQUEST_LOGIN_SUCCESS:
            return Object.assign({}, state, { loginToken: action.payload, isLoggedin: false })
        case REQUEST_LOGIN_FAILED:
            return Object.assign({}, state, { error: "Fetch Failed! Cannot retrieved data!", isLoggedin: false })
        default:
            return state;
    }
}