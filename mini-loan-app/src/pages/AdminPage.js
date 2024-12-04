import React, { useState, useEffect, useContext } from 'react';
import axiosInstance from '../services/axiosInstance'; 
import { AuthContext } from '../context/AuthContext'; 
import { toast, ToastContainer } from 'react-toastify'; 
import 'react-toastify/dist/ReactToastify.css'; 

function AdminPage() {
  const [loans, setLoans] = useState([]); 
  const [error, setError] = useState(null); 
  const [loading, setLoading] = useState(true); 
  const [approvingLoanId, setApprovingLoanId] = useState(null); 
  const { token, user } = useContext(AuthContext); 

  useEffect(() => {
    if (user && user.role !== 'ROLE_ADMIN') {
      window.location.href = '/login'; 
    }
  }, [user]);

  useEffect(() => {
    const fetchPendingLoans = async () => {
      try {
        const response = await axiosInstance.get('/loans/status/pending', {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        });
        setLoans(response.data); 
      } catch (error) {
        setError('Failed to fetch loans'); 
      } finally {
        setLoading(false); 
      }
    };

    fetchPendingLoans();
  }, [token]); 

  
  const handleApproveLoan = async (loanId) => {
    setApprovingLoanId(loanId); 
    try {
      await axiosInstance.patch(`/loans/approve/${loanId}`, null, {
        headers: {
          Authorization: `Bearer ${token}`, 
        },
      });
      toast.success('Loan approved successfully!'); 

    
      setLoans((prevLoans) =>
        prevLoans.map((loan) =>
          loan.id === loanId ? { ...loan, status: 'APPROVED' } : loan
        )
      );
    } catch (error) {
      toast.error('Failed to approve loan. Please try again.'); 
    } finally {
      setApprovingLoanId(null); 
    }
  };

  return (
    <div className="container my-3">
      {/* <h2>Admin Dashboard</h2> */}

      {error && <div className="alert alert-danger">{error}</div>}

      {loading && <div>Loading...</div>}

      <div>
        <h3>Pending Loans</h3>
       
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Sr.No.</th>
              <th>Username</th>
              <th>Amount</th>
              <th>Term (weeks)</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            
            {loans.map((loan, index) => (
              loan.status === 'PENDING' && (
                <tr key={loan.id}>
                  <td>{index + 1}</td>
                  <td>{loan.username}</td>
                  <td>{loan.amountRequired}</td>
                  <td>{loan.loanTerm}</td>
                  <td>{loan.status}</td>
                  <td>
                    <button
                      className="btn btn-success"
                      onClick={() => handleApproveLoan(loan.id)} 
                      disabled={approvingLoanId === loan.id} 
                    >
                      {approvingLoanId === loan.id ? 'Approving...' : 'Approve'}
                    </button>
                  </td>
                </tr>
              )
            ))}
          </tbody>
        </table>
      </div>

      <ToastContainer />
    </div>
  );
}

export default AdminPage;
