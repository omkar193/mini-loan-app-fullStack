import React from 'react';
import { Link } from 'react-router-dom';

function HomePage() {
  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
      <div className="text-center mt-n50" style={{ marginTop: '-200px' }}> 
        <h1 className="mb-4 text-primary">Welcome to Mini Loan App</h1>
        <p className="mb-4 text-muted">Manage your loans easily and efficiently.</p>
        <Link to="/register" className="btn btn-primary mx-2">Register</Link>
        <Link to="/login" className="btn btn-secondary mx-2">Login</Link>
      </div>
    </div>
  );
}

export default HomePage;
