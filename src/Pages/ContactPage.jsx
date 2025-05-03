import React, { useEffect, useState } from 'react';
import { useAuth } from '../Context/AuthContext';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import ContactList from '../Components/ContactList';
import SearchBar from '../Components/SearchBar';
import Pagination from '../Components/Pagination';


function ContactPage() {
    const { user } = useAuth();
    const navigate = useNavigate();
    
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [contacts, setContacts] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [filteredContacts, setFilteredContacts] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const contactsPerPage = 5;
    
    // Debug information
    console.log("ContactPage render - User:", user);
    console.log("User ID:", user?.id);


    
    useEffect(() => {
        // Don't immediately redirect - check after a short delay
        const authCheck = setTimeout(() => {
            if (!user) {
                navigate('/login');
            } else {
                fetchContacts();
            }
        }, 500);
        
        return () => clearTimeout(authCheck);
    }, [user, navigate]);
    
    const fetchContacts = () => {
        const dummyContacts = [
          { id: 1, firstName: 'Alice', lastName: 'Smith', phone: '123-456-7890' },
          { id: 2, firstName: 'Bob', lastName: 'Johnson', phone: '987-654-3210' },
          { id: 3, firstName: 'Charlie', lastName: 'Brown', phone: '555-555-5555' },
          { id: 4, firstName: 'David', lastName: 'Lee', phone: '111-222-3333' },
          { id: 5, firstName: 'Eva', lastName: 'Williams', phone: '444-666-7777' },
          { id: 6, firstName: 'Fatima', lastName: 'Khan', phone: '888-999-0000' },
        ];
      
        setTimeout(() => {
          setContacts(dummyContacts);
          setFilteredContacts(dummyContacts);
          setLoading(false);
        }, 500); // simulate network delay
    };
    
    useEffect(() => {
        if (!contacts.length) return;
        const lower = searchTerm.toLowerCase();
        const filtered = contacts.filter(c =>
            c.firstName.toLowerCase().includes(lower) ||
            c.lastName.toLowerCase().includes(lower)
        );
        setFilteredContacts(filtered);
        setCurrentPage(1);
    }, [searchTerm, contacts]);
    
    const deleteContact = (id) => {
        if (!window.confirm("Are you sure you want to delete this contact?")) return;
        
        axios.delete(`/api/contacts/${id}`)
            .then(() => {
                const updated = contacts.filter(c => c.id !== id);
                setContacts(updated);
            })
            .catch(err => {
                alert("Failed to delete contact: " + err.message);
            });
    };
    
    const indexOfLast = currentPage * contactsPerPage;
    const indexOfFirst = indexOfLast - contactsPerPage;
    const currentContacts = filteredContacts.slice(indexOfFirst, indexOfLast);
    const totalPages = Math.ceil(filteredContacts.length / contactsPerPage);
    
    if (loading) {
        return <div className="contacts-header"><h3>Loading contacts...</h3></div>;
    }
    
    if (error) {
        return (
            <div className="contact-page-container">
                <h3>Error</h3>
                <p>{error}</p>
                <button className="retry-btn" onClick={fetchContacts}>Try Again</button>
                <button className="go-to-login-btn" onClick={() => navigate('/login')}>Go to Login</button>
            </div>
        );
    }
    
    return (
        <div id="main2" className="contact-page-container">
        
    
        <div className="contacts-header">
            <h2>Your Contacts ({filteredContacts.length})</h2>
        </div>
    
        <div className="card-list-header">
        <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
            <button 
                className="btn small" 
                onClick={() => navigate('/createContact')}
            >
                + New Contact
            </button>
        </div>
            
         
            
            {filteredContacts.length === 0 ? (
                <div className="no-contacts-message">
                    <p>No contacts found. {searchTerm ? 'Try a different search term or ' : ''}add a new contact.</p>
                </div>
            ) : (
                <>
                    <ContactList
                        contacts={currentContacts}
                        deleteContact={deleteContact}
                        navigate={navigate}
                    />
                    
                    <Pagination
                        totalPages={totalPages}
                        currentPage={currentPage}
                        setCurrentPage={setCurrentPage}
                    />
                </>
            )}
        </div>
    );
}

export default ContactPage;
