import React, { useState, useEffect } from 'react';
import { useAuth } from '../Context/AuthContext';
import { useNavigate } from 'react-router-dom';
import ChangePasswordModal from '../Components/ChangePasswordModal';


function ProfilePage() {
    const { user, logout, changePassword } = useAuth();
    const navigate = useNavigate();
    const [showChangePasswordModal, setShowChangePasswordModal] = useState(false);

    useEffect(() => {
        if (!user) {
            navigate('/login');
        }
    }, [user, navigate]);

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    const handleChangePassword = () => {
        setShowChangePasswordModal(true);
    };

    return (
        <div className="profile-wrapper">
            <div className="profile-card">
                <h2 className="profile-title">Your Profile</h2>
                {user ? (
                    <>
                        <div className="profile-info">
                            <p><strong>Username:</strong> {user.username}</p>
                            <p><strong>Email:</strong> {user.email}</p>
                            <p><strong>Full Name:</strong> {user.fullName}</p>
                        </div>
                        <div className="profile-buttons">
                            <button className="btn btn-blue" onClick={handleChangePassword}>Change Password</button>
                            <button className="btn btn-red" onClick={handleLogout}>Logout</button>
                        </div>

                        {showChangePasswordModal && (
                            <ChangePasswordModal
                                closeModal={() => setShowChangePasswordModal(false)}
                                changePassword={changePassword}
                            />
                        )}
                    </>
                ) : (
                    <p className="loading-text">Loading user information...</p>
                )}
            </div>
        </div>
    );
}

export default ProfilePage;
