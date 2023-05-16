import React from 'react'

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'

import Header from '../include/Header';
import Footer from '../include/Footer';

import hoaphuong from '../custom/img/hoaphuong.JPG'


export default function Cart() {
  return (
    <body style={{'background-color': '#f4f4f6'}}>
      <Header/>
      <div class="thanhdiachi">
      <div class="thanhdiachi1">
        <h1 class="thanhdiachi2">Flora FLowers Shop / Cart</h1>
      </div>
      </div>

      <div style={{'width': '100%','height':'30px','background-color':'#ffffff'}}></div>
      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>

      <div class="giohang">
      <div class="giohang1">
        <div class="giohang_thanhtitle">
            <div class="giohang_title" style={{'width': '382px','margin-left': '10px'}}>Product</div>
            <div class="giohang_title" style={{'width': '200px'}}>Type</div>
            <div class="giohang_title" style={{'width': '128px'}}>Quantity</div>
            <div class="giohang_title">Price</div>
        </div>
        <div style={{'width': '100%','height':'5px','background-color':'#f4f4f6'}}></div>

        <div class="giohangd">
          <div class="giohang_div">
                <img class="giohang_img"src={hoaphuong}></img>
          </div>
          <div class="giohang_tensp">Hoa phuong do</div>
          <div class="giohang_type">
                <div class="giohang_type1">Color: Blue</div>
                <div class="giohang_type1">Type: Wedding</div> 
          </div>
          <div class="giohang_quantity">5</div>
          <div class="giohang_price">$10.5</div>

        </div>
        <div style={{'width': '100%','height':'20px','background-color':'#f4f4f6'}}></div>
      </div>
      <div class="giohang2">
        <div class="giohang2_info">Total Cart</div>
        <div class="giohang2_total" style={{'margin-top': '10px'}}>Total price: $50.5 </div>
        <div class="giohang2_total" style={{'width': '170px','border-bottom': '1px solid gray'}}>Tax: $5.5  </div>
        <div class="giohang2_total" style={{'margin-top': '10px'}}>Total: $100.5  </div>
        <div style={{'width': '100%','height':'50px','float':'left','background-color':'#ffffff'}}></div>
        <div class="giohang2_checkout">
            <a href="order" class="giohang2_checkout_button">Check out</a>
        </div>
      </div>
      </div>

      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>
      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>

    <Footer/>
    </body>
  )
}
