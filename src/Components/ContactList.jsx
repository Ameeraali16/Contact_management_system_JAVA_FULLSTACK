// ContactList.js
import React from 'react';

function ContactList({ contacts, deleteContact, navigate }) {
    return (
        <div className="card-list">
            {contacts.map(contact => (
                <div className="card" key={contact.id}>
                    <h3>{contact.firstName} {contact.lastName}</h3>
                    <p>{contact.phoneNumbers?.[0]?.number}</p>
                    <div className="btn-group">
                        <button className="btn small" onClick={() => navigate(`/updateContact/${contact.id}`)}>
                            Edit
                        </button>
                        <button className="btn danger small" onClick={() => deleteContact(contact.id)}>
                            Delete
                        </button>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default ContactList;
