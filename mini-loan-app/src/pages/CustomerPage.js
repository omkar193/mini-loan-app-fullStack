import React, { useState, useEffect, useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import axiosInstance from '../services/axiosInstance'; 
import { toast, ToastContainer } from 'react-toastify'; 
import 'react-toastify/dist/ReactToastify.css'; 

function CustomerPage() {
  const { user, token } = useContext(AuthContext); 
  const [loans, setLoans] = useState([]);  
  const [showLoanForm, setShowLoanForm] = useState(false);  
  const [amount, setAmount] = useState(''); 
  const [term, setTerm] = useState(''); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedLoan, setSelectedLoan] = useState(null);

  useEffect(() => {
    const fetchLoans = async () => {
      try {
        const response = await axiosInstance.get(`/loans/user/${user.id}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setLoans(response.data); 
      } catch (err) {
        setError('Failed to fetch loans.');
      } finally {
        setLoading(false);
      }
    };

    if (user) {
      fetchLoans(); 
    }
  }, [user, token]);

  const handleSubmitLoanRequest = async () => {
    try {
      setLoading(true);
      const response = await axiosInstance.post('/loans/create', {
        userId: user.id, 
        username: user.username,
        amountRequired: amount,
        loanTerm: term,
      }, {
        headers: {
          Authorization: `Bearer ${token}`, 
        },
      });
      setLoans((prevLoans) => [...prevLoans, response.data]); 
      setShowLoanForm(false); 
      toast.success('New loan created successfully!');
    } catch (err) {
      setError('Failed to create loan request.');
      toast.error('Failed to create loan request!');
    } finally {
      setLoading(false);
    }
  };

  const handleCancelLoanRequest = () => {
    setShowLoanForm(false);
  };

 
    const handleRepayment = async () => {
    if (!selectedLoan || !amount || amount <= 0) {
      toast.error('Please select a loan and enter a valid repayment amount.');
      return;
    }
  
    try {
      setLoading(true); 
      
      const response = await axiosInstance.post(
        `/repayments/${selectedLoan.id}`,
        { amount }, 
        {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        }
      );
  
      toast.success('Repayment submitted successfully!');
  
      setLoans((prevLoans) =>
        prevLoans.map((loan) => (loan.id === selectedLoan.id ? response.data : loan))
      );  
      
      setAmount('');
      setSelectedLoan(null);
    } catch (err) {
      toast.error(err.response?.data?.message || 'Failed to make repayment. Please try again.');
    } finally {
      setLoading(false);
    }
  };
  
  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div className="alert alert-danger">{error}</div>;
  }

  return (
    <div className="container my-3">
      {/* <h2>Welcome, {user.username}</h2>   */}
    
      <div className="d-flex justify-content-center mb-3">
        <button 
          className="btn btn-primary mx-2" 
          onClick={() => setShowLoanForm(!showLoanForm)}
        >
          {showLoanForm ? 'Cancel Loan Request' : 'Request New Loan'}
        </button>

        <button 
          className="btn btn-info mx-2" 
          onClick={() => { /* need to check it later "My Loans" logic here */ }}
        >
          My Loans
        </button>
      </div>
      
      {showLoanForm && (
        <div className="loan-request-form">
          <h3>Request New Loan</h3>
          <div className="mb-3">
            <label htmlFor="amount" className="form-label">Amount</label>
            <input 
              type="number" 
              id="amount" 
              className="form-control"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="term" className="form-label">Term (weeks)</label>
            <input 
              type="number" 
              id="term" 
              className="form-control"
              value={term}
              onChange={(e) => setTerm(e.target.value)}
              required
            />
          </div>
          <button className="btn btn-success" onClick={handleSubmitLoanRequest}>Submit Request</button>
        </div>
      )}

      {!showLoanForm && loans.length > 0 && (
        <div className="loan-list">
          <h3>My Loans</h3>
          <table className="table table-striped">
            <thead>
              <tr>
                <th>#</th>  
                <th>Amount</th>
                <th>Term (weeks)</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {loans.map((loan, index) => (
                <tr key={loan.id}>
                  <td>{index + 1}</td> 
                  <td>{loan.amountRequired}</td>
                  <td>{loan.loanTerm}</td>
                  <td>{loan.status}</td>
                  <td>
                    <button
                      className="btn btn-primary"
                      onClick={() => setSelectedLoan(loan)} 
                    >
                      Show Repayments
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      
      {selectedLoan && (
        <div>
          <h3>Repayments for Loan amount {selectedLoan.amountRequired}</h3>
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Sr.No.</th>
                <th>Due Date</th>
                <th>Amount</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {selectedLoan.repayments.map((repayment, index) => (
                <tr key={repayment.id || index}>
                  <td>{index + 1}</td>
                  <td>{repayment.dueDate}</td>
                  <td>{repayment.amount}</td>
                  <td>{repayment.status}</td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="mt-3">
            <label htmlFor="repaymentAmount" className="form-label">Repayment Amount:</label>
            <input
              type="number"
              className="form-control"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
            />
            <button className="btn btn-success mt-3" onClick={handleRepayment}>Pay Now</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default CustomerPage;
