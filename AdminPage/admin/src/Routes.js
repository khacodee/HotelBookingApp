import Home from "./pages/home/Home";
import Login from "./pages/login/Login";
import ListHotel from "./pages/list/ListHotel";
import { createBrowserRouter } from "react-router-dom";
import NewHotel from "./pages/newHotel/NewHotel";
import ViewDetail from "./pages/viewDetail/ViewDetail";
import UpdateHotel from "./pages/updateHotel/UpdateHotel";


const routers = createBrowserRouter([
    {
      path: "/",
      element: <Login></Login>,
    },
    {
      path: "/home",
      element: <Home></Home>,
    },
    {
      path: "/hotels",
      element: <ListHotel></ListHotel>,
    },
    {
      path: "/newHotel",
      element: <NewHotel></NewHotel>,
    },
    {
      path: "/hotel/:id",
      element: <ViewDetail></ViewDetail>
    },
    {
      path: "/hotel-update/:hotelId",
      element: <UpdateHotel></UpdateHotel>
    }
    
])


export default routers;