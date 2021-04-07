import RoomRow from '../Table/RoomRow'
import './table.css'
import { useHistory } from "react-router-dom";

const Table = (props) => {
    let history = useHistory();
    const redirectToAddEditRoom = () => {
        history.push("/admin/rooms/add");
    }
    
    return (
        <div>
            <table className="table__container">
                <thead>
                    <tr>
                        <td colSpan={4} className="table__header__td_search">
                            <input type="search" placeholder="search" className="table__header__search" />
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
                    {/* mapeaza roomurile si da ca parametru la roomrow */}
                    {props.store.requestRooms.rooms.map((room, i) =>
                        <RoomRow room={room} key={i} store={props.store} />
                    )}
                </tbody>
            </table>
        </div>
    )
}

export default Table
