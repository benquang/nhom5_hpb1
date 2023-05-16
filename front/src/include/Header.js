import React from 'react'
import { Link, useNavigate } from "react-router-dom";

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'
import logo1 from '../custom/img/logo.jpg'


export default function Header() {
  let navigate = useNavigate();

  let retrieveuser = localStorage.getItem('user');

  function handleClick() {
    if (retrieveuser) {
      localStorage.removeItem('user');
      navigate("/");
    }
    else {
      navigate("/login");
    }
  }

  return (
    <header className="header_area">
    <div class="main_menu">
    <div class="container" style={{'max-width': '1188px'}}>
    <nav class="navbar navbar-expand-lg navbar-light w-100">
      <a href="/shop" class="navbar-brand logo_h">
        <img src={logo1} alt="" style={{'width':'50%','height': '50%'}}/>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <div class="collapse navbar-collapse offset w-100" id="navbarSupportedContent">
      <div class="row w-100 mr-0">
      <div class="col-lg-8 pr-0">
      <ul class="nav navbar-nav center_nav pull-right">
        <li class="nav-item active">
            <a class="nav-link" href="/" style={{'font-size': '16px','color': '#d3232a'}}>Home</a>
        </li>
        <li class="nav-item submenu dropdown">
                <a style={{'font-size': '16px','color': 'black'}} href="/shop" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">Shop</a>
                <ul class="dropdown-menu">
                </ul>
        </li>
        <li class="nav-item submenu dropdown">
                <a style={{'font-size': '16px','color': 'black'}} href="/cart" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">Cart</a>
        </li>
        <li class="nav-item submenu dropdown">
                <a href="order" style={{'font-size': '16px','color': 'black'}} class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">Order</a>
        </li>
        <li class="nav-item">
                <a style={{'font-size': '16px','color': 'black'}} class="nav-link" href="user">Contact</a>
        </li>
      </ul>
      </div>

      <div class="col-lg-4 pr-0">
      <ul class="nav navbar-nav navbar-right right_nav pull-right">
        <li class="nav-item">
                <a style={{'font-size': '20px','margin-right': '10px'}} href="#">
                </a>
        </li>
        <li class="nav-item" style={{'line-height':'80px'}}>
                {/*
                <a href="user" style={{'font-size': '20px'}}>
                  <span style={{'line-height': '65px','color': '#0d0d1f'}}> Hi guest!</span>
                   
                </a>
                <a href="user" style={{'font-size': '20px'}} >
                  <span style={{'line-height': '65px','padding-left': '10px'}}> Login</span>
                   
                </a>
                */}
                                    
                 <a href="profile" style={{'font-size': '20px'}}>
                  <span style={{'line-height': '65px','color':'#007bff'}}>{retrieveuser ? retrieveuser : 'Guest'}</span>
                   
                </a>
                <a onClick={handleClick} style={{'font-size': '20px','color': 'rgb(0, 123, 255)'}} >
                  <span style={{'line-height': '65px','padding-left': '10px'}}>{retrieveuser ? 'Logout' : 'Login'}</span>      
                </a>      
        </li>
        <li class="nav-item">
                <a style={{'font-size': '20px'}} href="#"></a>
        </li>
        <li class="nav-item">
                <a style={{'font-size': '20px'}} href="#">
                </a>
        </li>
      </ul>
      </div>
      </div>
      </div>
    </nav>
    </div>

    </div>

    </header>
  )
}
