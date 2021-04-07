import React from 'react'
import './rooms.css'
import { useDispatch } from 'react-redux'
import { useEffect } from 'react';
import Table from '../Table/Table'

const Rooms = (props) => {
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(props.store.onRequestRooms())
    }, [props.store.requestDeleteRoom.isPendingDeleteRoom])

    return (
        <div className="rooms_continer">
            <Table store={props.store} reserved={false}/>
        </div>
    )
}

export default Rooms