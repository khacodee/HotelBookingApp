import React, { useState, useEffect } from "react";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import DriveFolderUploadOutlinedIcon from "@mui/icons-material/DriveFolderUploadOutlined";
import axios from "axios";
import "./updateHotel.scss";
import { useParams } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
const UpdateHotel = ({  }) => {
    const { hotelId } = useParams();

  const [file, setFile] = useState(null);
  const [info, setInfo] = useState({
    name: "",
    location: "",
    description: "",
    image: "",
    price: 0,
    isActive: true,
  });

  useEffect(() => {
    // Gọi API để lấy thông tin của khách sạn cần cập nhật và hiển thị trong form
    axios.get(`http://localhost:8080/app/hotels/${hotelId}`)
      .then((response) => {
        const hotelData = response.data.data;
        setInfo({
          name: hotelData.name,
          location: hotelData.location,
          description: hotelData.description,
          image: hotelData.image,
          price: hotelData.price,
          isActive: hotelData.active,
        });
      })
      .catch((error) => {
        console.error("Error fetching hotel data:", error);
      });
  }, [hotelId]);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setInfo((prev) => ({ ...prev, [id]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = new FormData();
    if (file) {
      // Nếu có ảnh mới được chọn, thêm vào FormData để upload
      data.append("file", file);
      data.append("upload_preset", "q5p5tehz");
    }

    try {
      let updatedImageUrl = info.image; // Giữ nguyên URL của ảnh cũ
    if (file) {
      // Nếu có ảnh mới, upload và lấy URL mới
      const uploadRes = await axios.post(
        "https://api.cloudinary.com/v1_1/dktcz2yl6/image/upload",
        data
      );
      updatedImageUrl = uploadRes.data.url; // Lấy URL mới của ảnh
    }

      const updatedHotel = {
        ...info,
        image: updatedImageUrl,
      };

      await axios.put(`http://localhost:8080/app/hotels/${hotelId}`, updatedHotel);
      toast.success("Update Hotel Successfully!");

      // Cập nhật thành công, có thể thực hiện các hành động khác
      setFile(null);
      console.log("Hotel updated:", updatedHotel);
    } catch (err) {
      console.error("Error updating hotel:", err);
      toast.error("Failed to update hotel!");
    }
  };
  console.log(info);
  return (
    <div className="new">
        <Sidebar />
        <div className="newContainer">
        <Navbar />
        <div className="top">
          <h1>Create New Hotel</h1>
        </div>
        <div className="bottom">
        <div className="left">
            <img
              src={
                file
                  ? URL.createObjectURL(file)
                  : info.image || "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg"
              }
              alt=""
            />
          </div>
          <div className="right">
      <form onSubmit={handleSubmit}>
      <div className="formInput">
        <label>Hotel Name</label>
        <input
          type="text"
          placeholder="Hotel Name"
          id="name"
          value={info.name}
          onChange={handleChange}
          />
         </div>
         <div className="formInput">
        <label>Location</label>
        <input
          type="text"
          placeholder="Location"
          id="location"
          value={info.location}
          onChange={handleChange}
        />
        </div>
        <div className="formInput">
        <label>Description</label>
        <input
          type="text"
          placeholder="Description"
          id="description"
          value={info.description}
          onChange={handleChange}
        />
        </div>
        <div className="formInput">
        <label htmlFor="file">
                  Image: <DriveFolderUploadOutlinedIcon className="icon" />
        </label>
        <input
                  type="file"
                  id="file"
                  onChange={(e) => setFile(e.target.files[0])}
                  style={{ display: "none" }}
                />
        </div>
        <div className="formInput">
        <label>Price</label>
        <input
          type="number"
          placeholder="Price"
          id="price"
          value={info.price}
          onChange={handleChange}
        />
        </div>
        <button type="submit">Submit</button>
      </form>
      <ToastContainer />
      </div>
      </div>
    </div>
    </div>
  );
};

export default UpdateHotel;
