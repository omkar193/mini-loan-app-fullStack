import React, { useState, useContext } from 'react';
import { AuthContext } from '../context/AuthContext'; 
import axiosInstance from '../services/axiosInstance'; 
import { toast } from 'react-toastify'; 

function CreateLoanForm() {
  const { user } = useContext(AuthContext); 
  const [loanAmount, setLoanAmount] = useState('');
  const [loanTerm, setLoanTerm] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    const loanData = {
      userId: user.id, 
      username: user.username, 
      amountRequired: loanAmount,
      loanTerm: loanTerm,
    };

    try {
     
      const response = await axiosInstance.post('/loans/create', loanData);
      toast.success('Loan created successfully!');
      
    } catch (err) {
      setError('Failed to create loan. Please try again.');
      toast.error('Failed to create loan.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container my-3">
      <h2>Create Loan Request</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="loanAmount" className="form-label">Loan Amount</label>
          <input
            type="number"
            id="loanAmount"
            className="form-control"
            value={loanAmount}
            onChange={(e) => setLoanAmount(e.target.value)}
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="loanTerm" className="form-label">Loan Term (in weeks)</label>
          <input
            type="number"
            id="loanTerm"
            className="form-control"
            value={loanTerm}
            onChange={(e) => setLoanTerm(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary" disabled={loading}>
          {loading ? 'Creating Loan...' : 'Create Loan'}
        </button>
      </form>
    </div>
  );
}

export default CreateLoanForm;
