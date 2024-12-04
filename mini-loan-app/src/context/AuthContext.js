import React, { createContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem('token')); 
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('user'))); 
  const navigate = useNavigate();

  useEffect(() => {
    if (token && user) {
      localStorage.setItem('token', token); 
      localStorage.setItem('user', JSON.stringify(user)); 
    }
  }, [token, user]);


  const logout = () => {
    setToken(null);
    setUser(null);
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login'); 
  };

  return (
    <AuthContext.Provider value={{ token, user, setToken, setUser, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
