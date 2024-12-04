import React, { useState } from 'react';
import { addRepayment } from '../services/api';

function RepaymentForm() {
  const [amount, setAmount] = useState('');

  const handleRepayment = async () => {
    try {
      await addRepayment({ amount });
      alert('Repayment successful!');
    } catch (error) {
      alert('Error adding repayment.');
    }
  };

  return (
    <div className="container my-3">
      <h2>Add Repayment</h2>
      <div className="input-group">
        <input
          type="number"
          className="form-control"
          placeholder="Enter repayment amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
        <button className="btn btn-success" onClick={handleRepayment}>Submit</button>
      </div>
    </div>
  );
}

export default RepaymentForm;
