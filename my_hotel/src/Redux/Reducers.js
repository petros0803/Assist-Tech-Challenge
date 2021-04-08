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
    UPDATE_TABLE_FALSE,
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
            return Object.assign({}, state, { error: '', loginToken: action.payload, isLoggingin: false })
        case REQUEST_LOGIN_FAILED:
            return Object.assign({}, state, { error: "User or password incorect. Please contact support if you forgot your account!", isLoggingin: false })
        case REQUEST_LOGOUT:
            return Object.assign({}, state, { loginToken: [], isLoggingin: false })
        default:
            return state;
    }
}

const initialState = {
    isPending: false,
    rooms: [],
    error: ''
}

export const requestRoomsReducer = (state = initialState, action = {}) => {
    switch (action.type) {
        case REQUEST_ROOMS_PENDING:
            return Object.assign({}, state, { isPending: true })
        case REQUEST_ROOMS_SUCCESS:
            return Object.assign({}, state, { rooms: action.payload, isPending: false })
        case REQUEST_ROOMS_FAILED:
            return Object.assign({}, { error: action.payload, isPending: false })
        default:
            return state
    }
}

const initialAddState = {
    isPendingAdd: false,
    roomToAdd: [],
    error: ''
}

export const requestAddRoomReducer = (state = initialAddState, action = {}) => {
    switch (action.type) {
        case REQUEST_ADDROOM_PENDING:
            return Object.assign({}, state, { isPending: true })
        case REQUEST_ADDROOM_SUCCESS:
            return Object.assign({}, state, { roomToAdd: action.payload, isPending: false })
        case REQUEST_ADDROOM_FAILED:
            return Object.assign({}, state, { error: action.payload, isPending: false })
        default:
            return state
    }
}

const initialDeleteRoomState = {
    isPendingDeleteRoom: false,
    successMessage: '',
    error: '',
}

export const requestDeleteRoomReducer = (state = initialDeleteRoomState, action = {}) => {
    switch (action.type) {
        case REQUEST_DELETEROOM_PENDING:
            return Object.assign({}, state, { isPendingDeleteRoom: true })
        case REQUEST_DELETEROOM_SUCCESS:
            return Object.assign({}, state, { successMessage: action.payload, isPendingDeleteRoom: false })
        case REQUEST_DELETEROOM_FAILED:
            return Object.assign({}, state, { error: action.payload, isPendingDeleteRoom: false })
        default:
            return state
    }
}

const initialGetRoomState = {
    isPendingGetRoom: false,
    room: [],
    error: ''
}

export const requestGetRoomReducer = (state = initialGetRoomState, action = {}) => {
    switch (action.type) {
        case REQUEST_GETROOM_PENDING:
            return Object.assign({}, state, { isPendingGetRoom: true })
        case REQUEST_GETROOM_SUCCESS:
            return Object.assign({}, state, { room: action.payload, isPendingGetRoom: false })
        case REQUEST_GETROOM_FAILED:
            return Object.assign({}, state, { error: action.payload, isPendingGetRoom: false })
        default:
            return state
    }
}

const initialUpdateTableState = {
    updateTable: false
}

export const updateTableReducer = (state = initialUpdateTableState, action = {}) => {
    switch (action.type) {
        case UPDATE_TABLE_TRUE:
            return Object.assign({}, state, { updateTable: true })
        case UPDATE_TABLE_FALSE:
            return Object.assign({}, state, { updateTable: false })
        default:
            return state
    }
}
