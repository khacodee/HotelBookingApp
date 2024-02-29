import "./viewDetail.scss";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import Chart from "../../components/chart/Chart";
import List from "../../components/table/Table";
import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from "axios";
const ViewDetail = () => {
    const [hotelPrice, setPrice] = useState({});
    const { id } = useParams();
    const [hotel, setHotel] = useState({}); 
    useEffect(() => {
        axios.get(`http://localhost:8080/app/hotels/${id}`
        )
          .then((response) => {
            setHotel(response.data.data);
            console.log(response.data.data);
            setPrice(response.data.data.price);
            
          })
          .catch((error) => {
            console.error("Error fetching room data:", error);
          });
      }, [id]);

        console.log(hotel);
  return (
    <div className="single">
      <Sidebar />
      <div className="singleContainer">
        <Navbar />
        <div className="top">
          <div className="left">
            <div className="editButton">Edit</div>
            <h1 className="title">Information</h1>
            <div className="item">
              <img
                src={hotel.image}
                alt=""
                className="itemImg"
              />
              <div className="details">
                <h1 className="itemTitle">{hotel.name}</h1>
                <div className="detailItem">
                  <span className="itemKey">Location:</span>
                  <span className="itemValue">{hotel.location}</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Description:</span>
                  <span className="itemValue">{hotel.description}</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Price:</span>
                  <span className="itemValue">
                  {hotelPrice.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}
                  </span>
                </div>
                <div className="detailItem">
                    <span className="itemKey">Active:</span>
                    <span className="itemValue" style={{ 
                     padding: '2px 5px',
                    borderRadius: '5px',
                    border: hotel.active ? '1px dotted green' : '1px dotted rgba(220, 20, 60, 0.6)' ,
                    cursor: 'pointer',
                    color: hotel.active ? 'green' : 'red',
             }}>
    {hotel.active ? 'Yes' : 'No'}
  </span>
</div>

              </div>
            </div>
          </div>
          <div className="right">
            <Chart aspect={3 / 1} title="User Spending ( Last 6 Months)" />
          </div>
        </div>
        <div className="bottom">
        <h1 className="title">Booking Request Approval</h1>
          <List/>
        </div>
      </div>
    </div>
  );
};

export default ViewDetail;
