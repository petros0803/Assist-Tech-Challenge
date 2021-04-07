import React, { useEffect, useState } from 'react'
import './roomrow.css'
import { AiOutlineEdit, AiOutlineDelete } from "react-icons/ai";
import { useDispatch } from 'react-redux'
import { useHistory } from "react-router-dom";

const RoomRow = (props) => {
    const [Star, setStar] = useState([])
    const dispatch = useDispatch();

    const displayFacilities = () => {
        const facilities = []
        Object.keys(props.room.facilities).map((item, i) => props.room.facilities[item] ? facilities.push(item) : '')
        setStar(facilities)
    }

    const [reRender, setReRender] = useState(0)

    useEffect(() => {
        displayFacilities();
    }, [reRender])

    const deleteRoom = (index) => {
        dispatch(props.store.onRequestDeleteRoom(index))
        alert("Room deleted!")
        setReRender(reRender + 1)
    }

    let history = useHistory();
    const updateRoom = (index) => {
        history.push(`/admin/rooms/edit/${index}`)
    }

    return (
        <tr className="table__roomDataRow">
            <td>
                {props.room.number}
            </td>
            <td>
                {Star.map((item, i) => item + ' ')}
            </td>
            <td>
                {props.room.beds}
            </td>
            <td>
                {props.room.price}
            </td>
            <td>
                {String(props.room.reserved)}
            </td>
            <td>
                {props.room.startDate?.substr(0, 10)}
            </td>
            <td>
                {props.room.expirationDate?.substr(0, 10)}
            </td>
            <td>
                <AiOutlineEdit onClick={() => updateRoom(props.room.id)} />
            </td>
            <td>
                <AiOutlineDelete onClick={() => deleteRoom(props.room.id)} />
            </td>
        </tr>
    )
}

export default RoomRow
