import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'

import Header from '../include/Header';
import Footer from '../include/Footer';




export default function Login() {
  let navigate = useNavigate();


  const { message } = useParams();

  const [user, setUser] = useState({
    userid: "",
    password: "",
  });

  const { userid, password } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };


  const onSubmit = async (e) => {
    e.preventDefault();
    const result = await axios.post("http://localhost:9092/users", user);
    if (result.data){

      localStorage.setItem('user',result.data.userid)

      navigate("/");
    }
    else {
      navigate("/login/User or password invalid");
    }
  };
  
  return (
    <body style={{'background-color': '#f4f4f6'}}>
      <Header/>
      <div class="thanhdiachi">
      <div class="thanhdiachi1">
        <h1 class="thanhdiachi2">Flora FLowers Shop / Login</h1>
      </div>
      </div>

      <div style={{'width': '100%','height':'30px','background-color':'#ffffff'}}></div>
      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>

      <div class="order">
      <div class="order1" style={{'padding-bottom': '20px','margin-top': '500px'}}>
      <form onSubmit={(e) => onSubmit(e)}>
            <input type="hidden" name="action" value="regist"></input>      
            <div class="order1_billing_address">Register</div>
            <div class="order1_billing_firstname">User Name</div>
            <div class="order1_billing_lastname">Password</div>
            <input type="text" name="username" class="order1_billing_firstname_tb"></input>
            <input type="password" name="password" class="order1_billing_lastname_tb"></input>
            <div class="order1_billing_address1">Full Name:</div>
            <input type="text" name="fullname" class="order1_billing_address1_tb"></input>
            <div class="order1_billing_firstname">Email</div>
            <div class="order1_billing_lastname">Phone</div>
            <input type="text" name="email" class="order1_billing_firstname_tb"></input>
            <input type="text" name="phone"class="order1_billing_lastname_tb"></input>
            <input type="submit" class="loginbutton" style={{'float': 'right','margin-right':'30px'}} value="Sign up"></input>
      </form>
      <h1 style={{'font-size':'20px'}}>$thong bao</h1>

      </div>
      <div class="order1" style={{'width': '500px','margin-left':'80px','padding-left': '30px','padding-bottom': '10px'}}>
        <form onSubmit={(e) => onSubmit(e)}>
            <input type="hidden" name="action" value="login"></input>
            <div class="order1_billing_address">Login</div>
            <div class="order1_billing_firstname" style={{'width': '100%','margin-right': '0px'}}>User </div>
            <input type="text" name="userid" value={userid} onChange={(e) => onInputChange(e)} class="order1_billing_firstname_tb"></input>
            <div class="order1_billing_firstname" style={{'width': '100%','margin-right': '0px'}}>Password</div>
            <input type="password" name="password" value={password} onChange={(e) => onInputChange(e)} class="order1_billing_lastname_tb" style={{'float': 'left'}}></input>
            <a href="user?action=forgotpassword" style={{'float': 'left','margin-left':'20px','color': '#d3232a','font-size': '20px'}}>Forgot Password</a>
            <button type="submit" class="loginbutton">Login</button>

        </form>
        <h1 style={{'font-size':'20px'}}>{message ? message : ''}</h1>
      </div>
      </div>
    <Footer/>
    </body>
  )
}
