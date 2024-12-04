import React, { useEffect, useState } from 'react';
import { getLoans } from '../services/api';

function LoanList() {
  const [loans, setLoans] = useState([]);

  useEffect(() => {
    async function fetchLoans() {
      try {
        const response = await getLoans(); 
        setLoans(response.data);
      } catch (error) {
        console.error('Error fetching loans:', error);
      }
    }
    fetchLoans();
  }, []);

  return (
    <div className="container my-3">
      <h2>Your Loans</h2>
      <div className="list-group">
        {loans.map((loan) => (
          <div key={loan.id} className="list-group-item">
            <h5>Amount: ${loan.amount}</h5>
            <p>Term: {loan.term} weeks</p>
            <p>Status: {loan.status}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default LoanList;
