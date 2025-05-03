import React, { useState, useEffect } from "react";
import logo from '../images/logo.png';
import { useAuth } from "../Context/AuthContext";
import { Link, useNavigate, useLocation } from 'react-router-dom';

function Navbar() {
    const [nav, setNav] = useState(false);
    const { user, logout } = useAuth();
    const navigate = useNavigate();
    const currentLocation = useLocation();

    const changeBackground = () => {
        if (window.scrollY >= 50) {
            setNav(true);
        } else {
            setNav(false);
        }
    };

    useEffect(() => {
        window.addEventListener('scroll', changeBackground);
        return () => {
            window.removeEventListener('scroll', changeBackground);
        };
    }, []);

    const scrollToSection = (sectionId) => {
        if (currentLocation.pathname !== '/') {
            navigate('/');
        }

        setTimeout(() => {
            const section = document.getElementById(sectionId);
            if (section) {
                section.scrollIntoView({ behavior: 'smooth' });
            }
        }, 100);
    };

    const scrollToTop = (e) => {
        if (currentLocation.pathname === '/') {
            e.preventDefault();
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
        }
    };

    const handleLogout = () => {
        logout();
        navigate('/');
        setTimeout(() => {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }, 100);
    };

    const handleNavigateToTop = (path) => {
        navigate(path);
        setTimeout(() => {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }, 100);
    };

    return (
        <nav className={nav ? 'nav active' : 'nav'}>
            <Link to="/" className="logo" onClick={scrollToTop}>
                <img src={logo} alt='ConTANK Logo' />
            </Link>
            <input type="checkbox" className="menu-btn" id="menu-btn" />
            <label className="menu-icon" htmlFor="menu-btn">
                <span className="nav-icon"></span>
            </label>
            <ul className="menu">
                {!user && (
                    <>
                        <li>
                            <Link to="/" onClick={scrollToTop}>Home</Link>
                        </li>
                        <li>
                            <a href="#" onClick={(e) => {
                                e.preventDefault();
                                scrollToSection('features-section');
                            }}>
                                Features
                            </a>
                        </li>
                        <li>
                            <a href="#" onClick={(e) => {
                                e.preventDefault();
                                scrollToSection('about-section');
                            }}>
                                About
                            </a>
                        </li>
                    </>
                )}

                {user ? (
                    <>
                        <li><a href="#" onClick={() => handleNavigateToTop('/ContactPage')}>Contacts</a></li>
                        <li><a href="#" onClick={() => handleNavigateToTop('/profile')}>View Profile</a></li>
                        <li><a href="#" onClick={(e) => { e.preventDefault(); handleLogout(); }}>Logout</a></li>
                    </>
                ) : (
                    <>
                        <li><a href="#" onClick={() => handleNavigateToTop('/login')}>Login</a></li>
                        <li><a href="#" onClick={() => handleNavigateToTop('/signup')}>Signup</a></li>
                    </>
                )}
            </ul>
        </nav>
    );
}

export default Navbar;
