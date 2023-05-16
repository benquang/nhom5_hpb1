import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link } from 'react-router-dom';

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'

import Header from '../include/Header';
import Footer from '../include/Footer';

import search_icon2 from '../custom/img/search_icon2.png'
import xuan from '../custom/img/xuan.png'
import ha from '../custom/img/ha.png'
import thu from '../custom/img/thu.png'
import dong from '../custom/img/dong.png'
import wedding from '../custom/img/wedding.png'
import valentine1 from '../custom/img/valentine1.png'

export default function Shop() {

  const [products,setProducts] = useState([])

  useEffect(()=>{
      loadProducts();
  },[]);

  const loadProducts=async()=>{
      const result=await axios.get("http://localhost:9095/products");
      setProducts(result.data);
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

      <div class="shop">
      <div class="shop1">
      <div class="shop2">
        <form action="shop" method="get" class="shop3">
          <input type="hidden" name="action" value="search"></input>
          <input type="text" name="tukhoa" class="shop4 no-outline" placeholder="type here"></input> 
          <input type="image" src={search_icon2} alt="Submit" class="shop5"></input>

        </form>
        <h3 class="men2">Category</h3>
        <ul class="men3">
          <li class="men4">
                <a class="men5" href="shop?action=spring">
                  <span class="men6">
                    <img class="men7"src={xuan} alt="a"></img>
                  </span>
                  <span class="men8">
                    <span class="men9">Spring</span>
                  </span>
                </a>
          </li>
          <li class="men4" style={{'margin-top': '12px'}}>
                <a class="men5" href="shop?action=summer">
                  <span class="men6">
                    <img class="men7"src={ha} alt="a"></img>
                  </span>
                  <span class="men8">
                    <span class="men9">Summer</span>
                  </span>
                </a>
          </li>
          <li class="men4" style={{'margin-top': '12px'}}>
                <a class="men5"  href="shop?action=autumn">
                  <span class="men6">
                    <img class="men7"src={thu} alt="a"></img>
                  </span>
                  <span class="men8">
                    <span class="men9">Autumn</span>
                  </span>
                </a>
          </li>
          <li class="men4" style={{'margin-top': '12px'}}>
                <a class="men5"  href="shop?action=winter">
                  <span class="men6">
                    <img class="men7"src={dong} alt="a"></img>
                  </span>
                  <span class="men8">
                    <span class="men9">Winter</span>
                  </span>
                </a>
          </li>
          <li class="men4" style={{'margin-top': '12px'}}>
                <a class="men5"  href="shop?action=wedding">
                  <span class="men6">
                    <img class="men7"src={wedding} alt="a"></img>
                  </span>
                  <span class="men8">
                    <span class="men9">Wedding</span>
                  </span>
                </a>    
          </li>
          <li class="men4" style={{'margin-top': '12px'}}>
                <a class="men5"  href="shop?action=valentine">
                  <span class="men6">
                    <img class="men7"src={valentine1} alt="a"></img>
                  </span>
                  <span class="men8">
                    <span class="men9">Valentine</span>
                  </span>
                </a>
          </li>
        </ul>


      </div>
      <div class="shop6">
      <div class="shop7">
       <h1 class="shop8">SHOP ALL PRODUCTS</h1>
      </div>
      <div class="shop9">
        <div style={{'width':'100%','height':'5px'}}></div>

      {
      products.map((product,index)=>(
        <Link to={`/products/${product.id}`} class="shop10_a" style={{'margin-bottom':'10px'}}>
                    <div class="shop10_div">
                        <img class="shop10_img"src={product.image}></img>
                    </div>
                    <div class="shop10_h ">{product.name}</div>
                    <div class="shop11_h_1">${product.price}</div>
                    <div class="shop11_h_2">{product.color}</div>
        </Link>
      ))
      }

      </div>
      </div>
      </div>
      </div>


    <Footer/>
    </body>
  )
}
