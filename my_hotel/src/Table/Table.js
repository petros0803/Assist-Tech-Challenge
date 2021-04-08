import React, { useState, useEffect } from 'react'
import RoomRow from '../Table/RoomRow'
import './table.css'
import { useHistory } from "react-router-dom";
import { useDispatch } from 'react-redux'
import notFound from '../Images/notFound.svg'

const Table = (props) => {
    const dispatch = useDispatch();
    let history = useHistory();
    const redirectToAddEditRoom = () => {
        history.push("/admin/rooms/add");
    }

    const checkReserved = () => {
        return props.reserved
    }

    useEffect(() => {
        dispatch(props.store.onRequestRooms())
    }, [props.store.updateTable.updateTable])

    const [searchText, setSearchText] = useState('')
    const rooms = props.store.requestRooms.rooms

    let filteredRooms = rooms.filter(roomsFiltered => {
        return roomsFiltered.number.includes(searchText)
    })

    return (
        <div>
            <table className="table__container">
                <thead>
                    <tr>
                        <td colSpan={4} className="table__header__td_search">
                            <input type="search" placeholder="search" className="table__header__search" onChange={e => setSearchText(e.target.value)} />
                        </td>

                        <td colSpan={5} className="table__header__td_button">
                            <button className="table__header__button" onClick={() => redirectToAddEditRoom()}> Add room </button>
                        </td>
                    </tr>
                    <tr className="table__header">
                        <th>Room number</th>
                        <th>Facilities</th>
                        <th>Beds</th>
                        <th>Price/day</th>
                        <th>Reserved</th>
                        <th>From date</th>
                        <th>To date</th>
                        <th colSpan={2}>Update and delete</th>
                    </tr>
                </thead>
                <tbody>
                    {props.reserved === false ?
                        filteredRooms.map((room, i) =>
                            <RoomRow room={room} key={i} store={props.store} />
                        ) :
                        filteredRooms.map((room, i) =>
                            room.reserved === true ?
                                <RoomRow room={room} key={i} store={props.store} /> : '')
                    }
                    {
                        filteredRooms.length === 0 &&
                        <td colSpan={8}>
                            <div className="table__notFoundDiv">
                                <img src={notFound} className="table__notFoundImage" />
                                <p className="table__notFoundText"> Room not found! </p>
                            </div>
                        </td>
                    }
                </tbody>
            </table>
        </div>
    )
}

export default Table
