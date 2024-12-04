import React, { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext'; 

function Navbar() {
  const navigate = useNavigate();
  const { user, setToken, setUser } = useContext(AuthContext); 

  const handleLogout = () => {
    setToken(null);
    setUser(null);
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    alert('Logged out successfully!');
    navigate('/login');
  };

  return (
    <nav className="navbar navbar-expand-lg">
      <div className="container">
        <Link to="/" className="navbar-brand"><b>Mini Loan App</b></Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto">
            
            {(user?.role === 'ROLE_CUSTOMER' || user?.role === 'ROLE_ADMIN') && (
              <li className="nav-item">
                <Link to="/customer" className="nav-link">Dashboard</Link>
              </li>
            )}
            
            {user?.role === 'ROLE_ADMIN' && (
              <li className="nav-item">
                <Link to="/admin" className="nav-link">Admin Dashboard</Link>
              </li>
            )}
          </ul>
          <ul className="navbar-nav ms-auto">
            {!localStorage.getItem('token') ? (
              <>
                <li className="nav-item">
                  <Link to="/login" className="nav-link">Login</Link>
                </li>
                <li className="nav-item">
                  <Link to="/register" className="nav-link">Register</Link>
                </li>
              </>
            ) : (
              <li className="nav-item">
                <button className="btn btn-outline-light" onClick={handleLogout}>Logout</button>
              </li>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
