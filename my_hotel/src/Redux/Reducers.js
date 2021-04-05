import {
    REQUEST_LOGIN_PENDING,
    REQUEST_LOGIN_SUCCESS,
    REQUEST_LOGIN_FAILED
} from './Constants'

const initialStateLogin = {
    isLoggingin: false,
    loginToken: [],
    error: ''
}

export const requestLoginReducer = (state = initialStateLogin, action = {}) => {
    switch (action.type) {
        case REQUEST_LOGIN_PENDING:
            return Object.assign({}, state, { isLoggingin: true })
        case REQUEST_LOGIN_SUCCESS:
            localStorage.setItem('User_Login_Token', action.payload.token);
            return Object.assign({}, state, { loginToken: action.payload, isLoggingin: false })
        case REQUEST_LOGIN_FAILED:
            return Object.assign({}, state, { error: "Fetch Failed! Cannot retrieved data!", isLoggingin: false })
        default:
            return state;
    }
}