import React, { useEffect, useState } from 'react';
import { getLoans, approveLoan } from '../services/api';

function AdminDashboard() {
  const [loans, setLoans] = useState([]);

  useEffect(() => {
    async function fetchLoans() {
      try {
        const response = await getLoans();
        setLoans(response.data.filter((loan) => loan.status === 'PENDING'));
      } catch (error) {
        console.error('Error fetching loans:', error);
      }
    }
    fetchLoans();
  }, []);

  const handleApprove = async (loanId) => {
    try {
      await approveLoan(loanId);
      alert('Loan approved!');
      setLoans(loans.filter((loan) => loan.id !== loanId));
    } catch (error) {
      alert('Error approving loan.');
    }
  };

  return (
    <div className="container my-3">
      <h2>Pending Loans</h2>
      <div className="list-group">
        {loans.map((loan) => (
          <div key={loan.id} className="list-group-item">
            <h5>Amount: ${loan.amount}</h5>
            <button className="btn btn-primary" onClick={() => handleApprove(loan.id)}>Approve</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AdminDashboard;
