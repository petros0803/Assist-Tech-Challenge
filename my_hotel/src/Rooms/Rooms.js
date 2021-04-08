import React from 'react'
import './rooms.css'
import Table from '../Table/Table'

const Rooms = (props) => {
    return (
        <div className="rooms_continer">
            <Table store={props.store} reserved={false}/>
        </div>
    )
}

export default Rooms