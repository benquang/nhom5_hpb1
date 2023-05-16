import React from 'react'

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'

import Header from '../include/Header';
import Footer from '../include/Footer';



export default function Order() {
  return (
    <body style={{'background-color': '#f4f4f6'}}>
      <Header/>
      <div class="thanhdiachi">
      <div class="thanhdiachi1">
        <h1 class="thanhdiachi2">Flora FLowers Shop / Order</h1>
      </div>
      </div>

      <div style={{'width': '100%','height':'30px','background-color':'#ffffff'}}></div>
      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>

      <div class="order">
      <form action="order" method="post" class="order1_form">
       <div class="order1">
            <div class="order1_billing_address">Billing Address</div>
            <div class="order1_billing_firstname">First Name</div>
            <div class="order1_billing_lastname">Last Name</div>
            <input type="text" name="firstname" class="order1_billing_firstname_tb"></input>
            <input type="text" name="lastname" class="order1_billing_lastname_tb"></input>
            <div class="order1_billing_firstname">Phone</div>
            <div class="order1_billing_lastname">Email</div>
            <input type="text" name="phone" class="order1_billing_firstname_tb"></input>
            <input type="text" name="email" class="order1_billing_lastname_tb"></input>
            <div class="order1_billing_address1">Address</div>
            <input type="text" name="address" class="order1_billing_address1_tb"></input>
            <div class="order1_billing_ordernote">Order Note</div>
            <textarea type="text" name="order_note" class="order1_billing_ordernote_tb"></textarea>
            <div class="order1_billing_firstname" style={{'width':'50%','margin-right':'0px'}}>Payment after delivery</div>
            <div class="order1_billing_firstname" style={{'width':'50%','margin-right':'0px'}}>PayPal</div>

            <input type="radio" name="payment" value="normal" style={{'width': '30%','margin-bottom':'10px','margin-right': '20%'}}></input>
            <input type="radio" name="payment" value="paypal" style={{'width': '8%','margin-bottom':'10px'}}></input>


       </div>
       <div class="order2">
         <div class="order2_2">
            <div class="order2_info">Order Product</div>
            <div class="order2_product_title">Product</div>
            <div class="order2_product_title" style={{'padding-left':'185px'}}>Sub Total</div>


            <div class="order2_product">Hoa phuong do</div>
            <div class="order2_subtotal">$50.5</div>
            <div class="order2_type">blue, wedding x5</div>

            <div class="order2_product">Hoa phuong do</div>
            <div class="order2_subtotal">$50.5</div>
            <div class="order2_type">blue, wedding x5</div>

            <div class="order2_total">TOTAL</div>
            <div class="order2_total_money">$100.5</div>
            <div class="order2_include_tax">(includes $5.5 Tax)</div>
            <input type="submit" class="order2_order" value="Order"></input>




         </div>
       </div>
      </form>
      </div>

      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>
      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>


    <Footer/>
    </body>
  )
}
