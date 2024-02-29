import "./datatable.scss";
import { DataGrid } from "@mui/x-data-grid";
import { Link, useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const DatatableHotel = () => {
  const [list, setList] = useState([]);
   const token = localStorage.getItem('token');


  // Calculate the starting and ending indices for the current page

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get(`http://localhost:8080/app/hotels`
        
        , {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
        );
        setList(response.data.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    }
    fetchData();
  }, []);

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "name", headerName: "Name", width: 200 },
    { field: "location", headerName: "Location", width: 150 },
    { field: "description", headerName: "Description", width: 250 },
    { field: "image", headerName: "Image", width: 200 },
    { field: "price", headerName: "Price", width: 120 },
    {
      field: "active",
      headerName: "Active",
      width: 100,
      valueGetter: (params) => {
        console.log("Active value:", params.row.active);
        return params.row.active ? "True" : "False";
      },
      renderCell: (params) => {
        const value = params.value === "True";
        return (
          <span style={{ 
            padding: '2px 5px',
            borderRadius: '5px',
            border: value ? '1px dotted green':'1px dotted rgba(220, 20, 60, 0.6)' ,
            cursor: 'pointer',
            color: value ? 'green' : 'red',
          }}>
            {params.row.active ? "True" : "False"}
          </span>
        );
      },
    },
  ];

  const actionColumn = [
    {
      field: "action",
      headerName: "Action",
      width: 200,
      renderCell: (params) => {
        return (
          <div className="cellAction">
            <Link to={`/hotel/${params.row.id}`} style={{ textDecoration: "none" }}>
            <VisibilityIcon className="viewButtonIcon" />
            </Link>
            <Link to={`/hotel-update/${params.row.id}`} style={{ textDecoration: "none" }}>
            <EditIcon className="updateButtonIcon" />
            </Link>
            <div
              className="deleteButton"
              onClick={() => handleDelete(params.row.id)}
            >
              <DeleteIcon className="deleteButtonIcon" />
            </div>
          </div>
        );
      },
    },
  ];

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/app/hotels/${id}`);
      setList((list) => list.filter((item) => item.id !== id));
      toast.success("Delete Hotel Successfully!");

    } catch (err) {
      console.error("Error deleting item:", err);
      toast.error("Failed to Delete hotel!");
    }
  };

  return (
    <div className="datatable">
      <div className="datatableTitle">
        List Hotel
        <Link to={`/newHotel`} className="link">
        <AddIcon /> Add New Hotel
        </Link>
      </div>
      <DataGrid
        rows={list}
        columns={columns.concat(actionColumn)}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 6,
            },
          },
        }}
        pageSizeOptions={[6]}
        checkboxSelection
        disableRowSelectionOnClick
      />
      <ToastContainer />
  </div>
  );
};

export default DatatableHotel;
