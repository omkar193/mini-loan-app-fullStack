import React from 'react';
import { useForm } from 'react-hook-form';
import { createLoan } from '../services/api';

function LoanForm() {
  const { register, handleSubmit, reset } = useForm();

  const onSubmit = async (data) => {
    try {
      await createLoan(data);
      alert('Loan created successfully!');
      reset();
    } catch (error) {
      alert('Error creating loan.');
    }
  };

  return (
    <div className="container my-3">
      <h2>Create Loan</h2>
      <form onSubmit={handleSubmit(onSubmit)} className="p-3 border rounded bg-light">
        <div className="mb-3">
          <label htmlFor="amount" className="form-label">Amount</label>
          <input type="number" className="form-control" id="amount" {...register('amount', { required: true })} />
        </div>
        <div className="mb-3">
          <label htmlFor="term" className="form-label">Loan Term (weeks)</label>
          <input type="number" className="form-control" id="term" {...register('term', { required: true })} />
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </div>
  );
}

export default LoanForm;
