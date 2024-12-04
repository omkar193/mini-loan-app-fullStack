import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext'; 
import Navbar from './components/Navbar';
import LoginForm from './components/LoginForm';
import RegisterForm from './components/RegisterForm';
import AdminPage from './pages/AdminPage';
import Dashboard from './pages/Dashboard';
import CustomerPage from './pages/CustomerPage';

import HomePage from './pages/HomePage';

function App() {
  return (
    <Router>
      <AuthProvider>  
      
        <Navbar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/register" element={<RegisterForm />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/admin" element={<AdminPage />} />
          <Route path="/customer" element={<CustomerPage />} />
        </Routes>
      
     </AuthProvider>
    </Router>
  );
}

export default App;
