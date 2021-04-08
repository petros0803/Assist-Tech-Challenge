import React from 'react'
import './navbar.css'
import {
    Nav,
    NavLink,
    Bars,
    NavMenu,
    NavBtn,
    NavBtnLink
} from './NavbarElements'
import logo from '../Images/logo.svg'
import { useDispatch } from 'react-redux'

const Navbar = (props) => {
    const dispatch = useDispatch();

    const logoutRedirect = () => {
        localStorage.removeItem('User_Login_Token')
        dispatch(props.store.onRequestLogout())
    }

    return (
        <>
            <Nav>
                <NavLink to='/admin/rooms'>
                    <img src={logo} alt='logo' />
                </NavLink>
                <Bars />
                <NavMenu>
                    <NavLink to='/admin/rooms'>
                        Rooms
                    </NavLink>
                    <NavLink to='/admin/reservations'>
                        Reservations
                    </NavLink>
                    <NavLink to='/admin/sendemail'>
                        Send promotion
                    </NavLink>
                    {/* <NavLink to='/sign-up' activeStyle>
                        Sign Up
                    </NavLink> */}
                </NavMenu>
                <NavBtn>
                    <NavBtnLink to='/login' onClick={() => logoutRedirect()}>Log out</NavBtnLink>
                </NavBtn>
            </Nav>
        </>
    )
}

export default Navbar