import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'
import rose from '../custom/img/rose5_bg.jpg'
import Header from '../include/Header';
import Footer from '../include/Footer';


export default function Home() {

  const { id } = useParams();
  const [product,setProduct] = useState([])

  useEffect(()=>{
      loadProduct();
  },[]);

  const loadProduct=async()=>{
      const result=await axios.get(`http://localhost:9095/products/${id}`);
      setProduct(result.data);
  };

  return (
    <body style={{'background-color': '#f4f4f6'}}>
      <Header/>
    

      <div class="thanhdiachi">
       <div class="thanhdiachi1">
        <h1 class="thanhdiachi2">Flora FLowers Shop / Shop</h1>
       </div>
      </div>

      <div style={{'width': '100%','height':'30px','background-color':'#ffffff'}}></div>


      <div class="detail">
      <div class="detail1">
       <div class="detail2">
        <img class="detail_img"src={product.image}></img>
        <div class="detail_lienquan">Related Product</div>

       </div>
       <div class="detail3">
            <div class="detail3_h">{product.name}</div>
            <div class="detail3_price">Price:</div>
            <div class="detail3_h1">$ {product.price}</div>
            <div class="detail3_flower">Flower:</div>
            <div class="detail3_h2">Hoa phuong</div>
            <div class="detail3_h3">{product.description}</div>
            <div class="detail3_color">Color:</div>
            <div class="detail3_h4">{product.color}</div>
            <div class="detail3_color" style={{'margin-top':'0px'}}>Type:</div>
            <div class="detail3_h4" style={{'line-height': '40px'}}>{product.type}</div>

            <form>
             <input type="hidden" name="flower" value="${variation.product}"></input>
             <input type="hidden" name="variation" value="${variation.variation_id}"></input>
              <div class="quantity">
                <input type="number" name="quantity" class="soluong1" min="0"></input>
              </div>
              <input type="submit" value="Go to Cart" class="addcart"></input>
            </form>
       </div>
      </div>
      </div>


    <Footer/>
    </body>
  )
}
