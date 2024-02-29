
import './App.scss';
import { Navigate, RouterProvider } from "react-router-dom";
import routers from "./Routes.js";



function App() {
  return (
    <RouterProvider 
    router={routers}>
  </RouterProvider>
  );
}

export default App;
