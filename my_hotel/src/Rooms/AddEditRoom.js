import React, { useState, useEffect } from 'react'
import './addeditroom.css'
import { useForm } from 'react-hook-form'
import { useDispatch } from 'react-redux'
import { Multiselect } from 'multiselect-react-dropdown';
import { useHistory } from "react-router-dom";
import { useParams } from 'react-router'
import api from '../Api-connections/Api-connections';
import { FaChevronCircleLeft } from 'react-icons/fa';

const AddEditRoom = (props) => {
    const dispatch = useDispatch();
    let history = useHistory();
    let { id } = useParams();
    const [inputRoomNumber, setInputRoomNumber] = useState();
    const [inputNumberOfBeds, setInputNumberOfBeds] = useState()
    const [inputPrice, setInputPrice] = useState()
    const [reserved, setReserved] = useState()
    const [options, setOptions] = useState([{ name: 'WIFI', id: 1 }, { name: 'AC', id: 2 }, { name: 'TV', id: 3 }, { name: 'NSR', id: 4 }])
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [errors, setErrors] = useState([])

    const getOptionByName = (name) => {
        switch (name) {
            case "wifi":
                return 1
            case "ac":
                return 2
            case "tv":
                return 3
            case "nsr":
                return 4
            default:
        }
    }

    const fetchUpdateRoomData = () => {
        fetch(api.rooms + `/${id}`, {
            method: 'GET'
        })
            .then(resp => resp.json())
            .then(data => {
                const selectedOptionsFromFetch = Object.entries(data.facilities).filter((x) => x[1] === true).map((x) => {
                    if (x[1] === true) {
                        return { name: x[0], id: getOptionByName(x[0]) }
                    }
                });

                setInputRoomNumber(data.number)
                setInputNumberOfBeds(data.beds)
                setInputPrice(data.price)
                setSelectedOptions(selectedOptionsFromFetch)
                setReserved(data.reserved)
            })
    }

    useEffect(() => {
        if (id !== undefined) {
            fetchUpdateRoomData()
        }

    }, [])

    const onSelect = (options) => {
        setSelectedOptions(options)
    }

    const onRemove = (options) => {
        setSelectedOptions(options)
    }

    const checkDataBeforeSend = () => {
        let error = []
        
        if (inputRoomNumber < 0 || inputRoomNumber > 999 || inputRoomNumber === undefined || inputRoomNumber.length === 0) {
            error.push("Room number should be between 0 and 999!")
        }
        if (inputNumberOfBeds < 0 || inputNumberOfBeds > 5 || inputNumberOfBeds === undefined || inputNumberOfBeds.length === 0) {
            error.push("Number of beds should be between 0 and 5!")
        }
        if (inputPrice < 0 || inputPrice === undefined || inputPrice.length === 0) {
            error.push("Price should be a positive value!")
        }

        setErrors(error)

        if (error.length > 0) {
            return false;
        }

        return true;
    }

    const addRoom = () => {
        if (checkDataBeforeSend()) {
            dispatch(props.store.onRequestAddRoom({
                "roomNumber": inputRoomNumber,
                "beds": inputNumberOfBeds,
                "price": inputPrice
            }, selectedOptions))
            history.push("/admin/rooms")
        }
    }

    const editRoom = () => {
        if (checkDataBeforeSend()) {
            const currentFacilities = [];
            Object.keys(selectedOptions.map((item, i) => currentFacilities.push(item.name.toUpperCase())))
            fetch(api.rooms + `/${id}`, {
                method: 'PUT',
                body: JSON.stringify({
                    "number": inputRoomNumber,
                    "beds": inputNumberOfBeds,
                    "facilities":{
                        "wifi": currentFacilities.indexOf("WIFI") > -1 ,
                        "ac": currentFacilities.indexOf("AC") > -1,
                        "tv": currentFacilities.indexOf("TV") > -1,
                        "nsr": currentFacilities.indexOf("NSR") > -1
                    },
                    "reserved": reserved,
                    "price": inputPrice
                }),
                headers: { 'Content-Type': 'application/json '}
            })
            .then(resp => {
                dispatch({type: 'UPDATE_TABLE_TRUE'})
                return resp.json()
            })
        }
        dispatch({type: 'UPDATE_TABLE_FALSE'})
        history.push("/admin/rooms")
    }

    return (
        <div>
            <form className="form__addEditContainer">
                <h1 className="form_addEditTitle"> {props.addRoom === true ? "Add room" : "Edit room"} </h1>
                <div className="form__addEditInputs">
                    <p className="form__p"> Room number </p>
                    {id === undefined ? <input type="number" className="form_inputs" name="roomNumber" onChange={event => setInputRoomNumber(event.target.value)} /> :
                        <input type="number" className="form_inputs" name="roomNumber" onChange={event => setInputRoomNumber(event.target.value)} value={inputRoomNumber} />}

                    <p className="form__p"> Number of beds </p>
                    {id === undefined ? <input type="number" className="form_inputs" name="numberOfBeds" onChange={event => setInputNumberOfBeds(event.target.value)} /> :
                        <input type="number" className="form_inputs" name="numberOfBeds" onChange={event => setInputNumberOfBeds(event.target.value)} value={inputNumberOfBeds} />}

                    <p className="form__p"> Price </p>
                    {id === undefined ? <input type="number" className="form_inputs" name="price" onChange={event => setInputPrice(event.target.value)} /> :
                        <input type="number" className="form_inputs" name="price" onChange={event => setInputPrice(event.target.value)} value={inputPrice} />}
                    {id === undefined ? <Multiselect
                        options={options}
                        displayValue={"name"}
                        onSelect={onSelect}
                        onRemove={onRemove}
                        className="form__multiselect"
                    /> :
                        <Multiselect
                            options={options}
                            displayValue={"name"}
                            onSelect={onSelect}
                            onRemove={onRemove}
                            className="form__multiselect"
                            selectedValues={selectedOptions}
                        />}
                    {id === undefined ? <button className="form__addEditRoomButton" type="button" onClick={() => addRoom()} > Add room</button> :
                        <button className="form__addEditRoomButton" type="button" onClick={() => editRoom()} > Edit room</button>}
                    {errors ? <div className="form_displayErrorsDiv"> {errors.map((index, i) => <p key={i} className="from__pDisplayErrors"> {index} </p>)} </div> : ''}
                </div>

            </form>
        </div>
    )
}

export default AddEditRoom
