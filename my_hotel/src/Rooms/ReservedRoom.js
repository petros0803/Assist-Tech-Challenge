import React from 'react'
import Table from '../Table/Table';

const ReservedRoom = (props) => {
    return (
        <div>
            <Table store={props.store} reserved={true}/>
        </div>
    )
}

export default ReservedRoom
