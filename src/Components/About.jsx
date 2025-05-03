import React from "react";
import { useNavigate } from 'react-router-dom';

function About({ image, title, button }) {
    const navigate = useNavigate();
    
    // Handle button click to navigate to signup page
    const handleButtonClick = () => {
      navigate('/signup');
    };
return (
    <div id = 'about'>
        <div className="about-image">
            <img src = {image} alt=''/>
        </div>
        <div className="about-text">
            <h2>{title}</h2>
            <p>
            ConTANK is an intuitive contact management tool designed to help you organize phone numbers and emails with notes, and reminders in one easy-to-use platform. Whether for personal use or professional networking, conTANK keeps your connections in one place, making it easier to stay organized and on top of your relationships.

            </p>
            <button onClick={handleButtonClick}>{button}</button>
        </div>
    </div>
)
}

export default About;