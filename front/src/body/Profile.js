import React from 'react'

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'

import Header from '../include/Header';
import Footer from '../include/Footer';


export default function Profile() {
  return (
    <body style={{'background-color': '#f4f4f6'}}>
      <Header/>

      <div class="thanhdiachi">
      <div class="thanhdiachi1">
        <h1 class="thanhdiachi2">Flora FLowers Shop / Profile</h1>
      </div>

      <div style={{'width': '100%','height':'30px','background-color':'#ffffff'}}></div>
      <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>

      <div class="profile">
      <form action="user" method="post">
       <div class="profile1">
        <div class="profile1_title">My info</div>
        <div class="profile1_name">
          <div class="profile1_name1">User:</div>
          <input type="text" class="profile1_name2 no-outline" value="baothy2906"></input>
        </div>
        <div class="profile1_name">
          <div class="profile1_name1">FullName:</div>
          <input type="text" class="profile1_name2 no-outline" value="Bao Thy"></input>
        </div>
        <div class="profile1_name">
          <div class="profile1_name1">Card:</div>
          <input type="text" class="profile1_name2 no-outline" value="0123456"></input>
        </div>
        <div class="profile1_name">
          <div class="profile1_name1">Balance:</div>
          <input type="text" class="profile1_name2 no-outline" value="$10595.5"></input>
        </div>
        <div style={{'width':'100%','height':'30px'}}></div>
       </div>
      </form>

      <div class="profile2" style={{'float': 'right'}}>
      <div class="profile2_2">
        <div class="profile2_info">$jkhsadfasdf876sdf.</div>
        <div class="profile2_product_title">Product</div>
        <div class="profile2_product_title" style={{'width': '25%'}}>Category</div>
        <div class="profile2_product_title" style={{'width': '25%'}}>Unit Price</div>
        <div class="profile2_product_title" style={{'width':'13%','text-align': 'right','float':'right'}} >Total</div>



        <div class="profile2_product">Hoa phuong do</div>
        <div class="profile2_category">Rose</div>
        <div class="profile2_unitprice">$50.5</div>
        <div class="profile2_subtotal">$10.5</div>
        <div class="profile2_type">blue, wedding x5</div>

        <div class="profile2_product">Hoa phuong do</div>
        <div class="profile2_category">Rose</div>
        <div class="profile2_unitprice">$50.5</div>
        <div class="profile2_subtotal">$10.5</div>
        <div class="profile2_type">blue, wedding x5</div>


        <div class="profile2_total">GRAND TOTAL</div>
        <div class="profile2_total_money">$100.5</div>
        <div class="profile2_include_tax">(includes $5.5 Tax)</div>
        <div class="profile2_total" style={{'width': '100%','margin-bottom':'15px'}}>INVOICED TO</div>
        <div class="profile2_firstname">First Name:</div>
        <div class="profile2_firstname1">Bao</div>
        <div class="profile2_lastname">Last Name:</div>
        <div class="profile2_lastname1">Thy</div>
        <div class="profile2_email">Email:</div>
        <div class="profile2_email1">baothy2906@gmail.com</div>
        <div class="profile2_firstname" style={{'margin-top': '8px'}}>Phone:</div>
        <div class="profile2_firstname1" style={{'margin-top': '8px'}}>0355109520</div>
        <div class="profile2_lastname" style={{'margin-top': '8px'}}>Address:</div>
        <div class="profile2_lastname1" style={{'width': '56%','margin-top': '8px'}}>tphcm</div>

        <div class="profile2_total" style={{'width':'100%','margin-top':'15px'}}>PAYMENT METHOD</div>
        <div class="profile2_firstname" style={{'margin-top': '8px','width': '100%','margin-bottom':'5px','color': '#0d0d1f'}}>After delivery</div>
        <div class="profile2_total" style={{'width': '100%','margin-bottom':'15px','margin-top': '25px'}}>STATUS</div>

        <div class="profile2_date">Order date:</div>
        <div class="profile2_date1">2022 - 05 - 04</div>
      </div>

      </div>



      </div>

  </div>

  <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>
  <div style={{'width': '100%','height':'30px','background-color':'#f4f4f6'}}></div>

    <Footer/>
    </body>
  )
}
