import React from "react";



function Header() {
    return (
        <div id = 'main'>
          
            <div className="name">
                <h1>
                    <span>
                    Welcome to ConTANK {' '}
                    </span>
                     Keep Your Connections in One Smart Tank.
                </h1>
                <p className="details">
                ConTANK helps you manage your contacts with notes, reminders, emails, and phone numbers — all in one organized space 
                </p>
                <a href="/signup" className="cv-btn">SignUp</a>
            </div>
        </div>
    )
}

export default Header;