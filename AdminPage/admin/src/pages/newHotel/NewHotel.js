import React, { useState } from "react";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import DriveFolderUploadOutlinedIcon from "@mui/icons-material/DriveFolderUploadOutlined";
import axios from "axios";
import "./newHotel.scss";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const NewHotel = () => {
  const [file, setFile] = useState(null);
  const [info, setInfo] = useState({
    name: "",
    location: "",
    description: "",
    image: "",
    price: 0,
    isActive: true,
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setInfo((prev) => ({ ...prev, [id]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = new FormData();
    data.append("file", file);
    data.append("upload_preset", "q5p5tehz");
    console.log(file);

    try {
      const uploadRes = await axios.post(
        "https://api.cloudinary.com/v1_1/dktcz2yl6/image/upload",
        data
      );

      const { url } = uploadRes.data;

      const newHotel = {
        ...info,
        image: url,
      };
    //   const list = await Promise.all(
    //     Object.values(files).map(async (file) => {
    //       const data = new FormData();
    //       data.append("file", file);
    //       data.append("upload_preset", "q5p5tehz");
    //       const uploadRes = await axios.post(
    //         "https://api.cloudinary.com/v1_1/dktcz2yl6/image/upload",
    //         data
    //       );
      

      await axios.post("http://localhost:8080/app/hotels", newHotel);
      // Thực hiện các hành động khác sau khi gửi dữ liệu thành công
      toast.success("Add New Hotel Successfully!");
      // Clear form fields
      setFile(null);
      setInfo({
        name: "",
        location: "",
        description: "",
        image: "",
        price: 0,
        isActive: true,
      });

      console.log("New hotel added:", newHotel);
    } catch (err) {
      console.error("Error adding new hotel:", err);
      toast.error("Failed to add new hotel!");
    }
  };

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
                  : "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg"
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

export default NewHotel;
