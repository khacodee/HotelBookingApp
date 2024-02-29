import "./table.scss";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import React, { useState, useEffect } from 'react';
import ReactPaginate from 'react-paginate';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const List = () => {
    const [list, setData] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);
    const hotelsPerPage = 10;
    const [update, setUpdate] = useState(0);
    const token = localStorage.getItem('token');
    useEffect(() => {
      const fetchData = async () => {
        try {
          const response = await fetch('http://localhost:8080/app/bookings?status=pending'
          , {
          headers: {
        Authorization: `Bearer ${token}`,
      },
        }
          );

          const data = await response.json();
          setData(data.data);
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      };
    
      fetchData();
    }, [update]);
    

    const handlePageClick = ({ selected }) => {
        setPageNumber(selected);
      };
    const indexOfLastHotel = (pageNumber + 1) * hotelsPerPage;
  const indexOfFirstHotel = indexOfLastHotel - hotelsPerPage;
  if(list !== null){
  const currentHotels = list.slice(indexOfFirstHotel, indexOfLastHotel);
}
  const pageCount = Math.ceil(list.length / hotelsPerPage);

  const updateBookingStatus = async (bookingId, newStatus) => {
    try {
      const response = await axios.put(
        `http://localhost:8080/app/booking/${bookingId}/${newStatus}`,
        {}, // Dữ liệu gửi đi nếu cần
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
            // Các headers khác nếu cần thiết
          },
        }
      );

      toast.success("Update Status Successfully!");
      if (response.status === 200 || response.status === 204) {
        setUpdate(prevUpdate => prevUpdate + 1); 
        console.log('Booking status updated successfully!');
      } else {
        console.error('Update failed:', response.statusText);
      }
    } catch (error) {
      console.error('Error updating status:', error);
      toast.error("Failed to Update Status!");
    }
  };
  

  
  return (
    <div>
    <TableContainer component={Paper} className="table">
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
          <TableCell className="tableCell">Booking Id</TableCell>
            <TableCell className="tableCell">Check In Date</TableCell>
            <TableCell className="tableCell">Check Out Date</TableCell>
            <TableCell className="tableCell">Total Price</TableCell>
            <TableCell className="tableCell">Payment Method</TableCell>
            <TableCell className="tableCell">Booking Status</TableCell>
            <TableCell className="tableCell">Action</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
        {list.length === 0 ? (
  <TableRow>
    <TableCell colSpan={7} className="tableCell">No data available</TableCell>
  </TableRow>
) : (
  list.map((row) => (
    <TableRow key={row.id}>
      <TableCell className="tableCell">{row.id}</TableCell>
      <TableCell className="tableCell">{row.checkInDate}</TableCell>
      <TableCell className="tableCell">{row.checkOutDate}</TableCell>
      <TableCell className="tableCell">{row.totalPrice}</TableCell>
      <TableCell className="tableCell">{row.paymentMethod}</TableCell>
      <TableCell className="tableCell">
        <span className={`status ${row.bookingStatus}`}>{row.bookingStatus}</span>
      </TableCell>
      <TableCell className="tableCell">
        {row.bookingStatus === 'PENDING' && (
          <div>
            <button className="approveButton" onClick={() => updateBookingStatus(row.id, 'approve')}>
              Approve
            </button>
            <button className="rejectButton" onClick={() => updateBookingStatus(row.id, 'reject')}>
              Reject
            </button>
          </div>
        )}
      </TableCell>
    </TableRow>
  ))
)}

        </TableBody>
      </Table>
    </TableContainer>
    <div className="pagination-container">
    <ReactPaginate
      previousLabel={"Previous"}
      nextLabel={"Next"}
      pageCount={pageCount}
      onPageChange={handlePageClick}
      containerClassName={"pagination"}
      previousLinkClassName={"previous"}
      nextLinkClassName={"next"}
      disabledClassName={"disabled"}
      activeClassName={"active"}
    />
  </div>
  <ToastContainer />
</div>
  );
};

export default List;
