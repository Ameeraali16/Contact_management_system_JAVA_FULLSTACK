// Context/AuthContext.jsx
import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
      setUser({
        token,
        id: 1, // 👈 Same dummy ID
        firstName: 'Dev',
        lastName: 'User'
      });
    }
  }, []);
  
  const login = (token) => {
    localStorage.setItem('authToken', token);
    setUser({
      token,
      id: 1, // 👈 Dummy user ID just for UI development
      firstName: 'Dev',
      lastName: 'User'
    });
  };
  
  const logout = () => {
    localStorage.removeItem('authToken');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
