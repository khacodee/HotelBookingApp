  import React from 'react';
  import {MDBContainer, MDBCol, MDBRow, MDBBtn, MDBIcon, MDBInput, MDBCheckbox } from 'mdb-react-ui-kit';
  import './login.css';
  import { useState } from 'react';
  import { Navigate } from 'react-router-dom';
  import { Link } from 'react-router-dom';
  import axios from 'axios';
  import { useNavigate} from 'react-router-dom';
  import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


  function Login() {
      const [username, setUsername] = useState("");
      const [password, setPassword] = useState("");
      const navigate = useNavigate();
      const [error, setError] = useState(null);
      
      
      const handleLogin = async () => {
      try {
        const response = await axios.post('http://localhost:8080/auth/login', {
          username: username,
          password: password,
        });
    
          toast.success("Login Successfully!");
          console.log('Login successful!');
          console.log(response.data);
          const token = response.data.data; // Lấy token từ response
          localStorage.setItem('token', token);
          navigate('/home');
        
      } catch (error) {
        console.error('Error while logging in:', error);
        toast.error("Login Failed!");
        if(error.response.data.error.code === 401){
          console.log('Login fail!');
          setError('Invalid username or password!!!');
        }
      }
    };
      
      const [showLogin, setShowLogin] = useState(true);

    const handleToggleScreen = () => {
      Navigate()
    };
    return (
      <MDBContainer fluid className="p-3 my-5 h-custom">

        <MDBRow>

          <MDBCol col='10' md='6'>
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" class="img-fluid" alt="Sample image" />
          </MDBCol>

          <MDBCol col='4' md='6'>

            <div className="d-flex flex-row align-items-center justify-content-center">

              <p className="lead fw-normal mb-0 me-3">Sign in with</p> 

              <MDBBtn floating size='md' tag='a' className='me-2'>
                <MDBIcon fab icon='facebook-f' />
              </MDBBtn>

              <MDBBtn floating size='md' tag='a'  className='me-2'>
                <MDBIcon fab icon='twitter' />
              </MDBBtn>

              <MDBBtn floating size='md' tag='a'  className='me-2'>
                <MDBIcon fab icon='linkedin-in' />
              </MDBBtn>

            </div>

            <div className="divider d-flex align-items-center my-4">
              <p className="text-center fw-bold mx-3 mb-0">Or</p>
            </div>

            <MDBInput wrapperClass='mb-4' label='UserName' id='formControlLg' type='username' size="lg" value={username}
          onChange={(e) => setUsername(e.target.value)}/>
            <MDBInput wrapperClass='mb-4' label='Password' id='formControlLg' type='password' size="lg" value={password}
          onChange={(e) => setPassword(e.target.value)}/>

            <div className="d-flex justify-content-between mb-4">
              <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
              <a href="!#">Forgot password?</a>
            </div>

            <div className='text-center text-md-start mt-4 pt-2'>
              <MDBBtn className="mb-0 px-5" size='lg' onClick={handleLogin}>Login</MDBBtn>
              {error && <div style={{ color: 'red' }}>{error}</div>}
              <p className="small fw-bold mt-2 pt-1 mb-2">Don't have an account? <Link to="/register">Register</Link></p>
              <ToastContainer />
            </div>

          </MDBCol>

        </MDBRow>


      </MDBContainer>
      
    );
  }

  export default Login;