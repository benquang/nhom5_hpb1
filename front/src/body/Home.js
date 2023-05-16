import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link } from 'react-router-dom';

import '../custom/css/bootstrap.css';
import '../custom/css/phai1.css';
import '../custom/css/shop.css';
import '../custom/css/style.css';
import '../custom/css/font-awesome.min.css'
import rose from '../custom/img/rose5_bg.jpg'
import Header from '../include/Header';
import Footer from '../include/Footer';
import spring from '../custom/img/spring.JPG'
import hoamai1 from '../custom/img/hoamai1.JPG'
import summer from '../custom/img/summer.JPG'
import hoaphuong from '../custom/img/hoaphuong.JPG'
import autumn from '../custom/img/autumn.JPG'
import hoasua1 from '../custom/img/hoasua1.JPG'
import winter from '../custom/img/winter.JPG'
import hoacuchoami from '../custom/img/hoacuchoami.JPG'
import data from '../custom/img/data.png'
import shipping from '../custom/img/shipping.png'
import refund from '../custom/img/refund.png'
import return1 from '../custom/img/return.png'
import securepayment from '../custom/img/securepayment.png'
import feature_flower_banner from '../custom/img/feature_flower_banner.JPG'

export default function Home() {
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
    <section class="home_banner_area mb-40" style={{'margin-bottom': '0px'}}>
        <div class="banner_inner d-flex align-items-center" style={{backgroundImage: `url(${rose})`,'background-size': 'cover','background-repeat': 'no-repeat'}}>
        <div class="container">
        <div class="banner_content row">
        <div class="col-lg-12" style={{'padding-left': '0px'}}>
        <p class="sub text-uppercase">WELCOME TO Summoner's Rift</p>
        <h3><span style={{'color': '#d3232a'}}>Group</span> 05 <br />Flower <span style={{'color': '#d3232a'}}>Shop</span></h3>
        <h4>Having a good day and a Tet vui ve!</h4>
        <a class="main_btn mt-40" href="#" style={{'background':'white','border':'white','color':'#d3232a','font-size':'16px'}}>View Collection</a>
        </div>
        </div>
        </div>

        </div>

    </section> 


    <div style={{'width':'584px','height':'2px','background-color':'#f4f4f6'}}></div>
    <div class="daitutao "style={{'font-size':'35px'}}>
        Featured Categories
      </div>


    <div class="hp-mod-card card-official-stores J_OfficialStores">
    <div class="hp-mod-card-header"></div>
    <div class="card-official-stores-content J_OfficialStoresItems">
      <section class="banner-slider" id="officialSlide">
      <div class="card-official-stores-wrapper" data-tag="shops" style={{'min-height':'450px'}}>
        <a class="card-official-stores-box hp-mod-card-hover align-left" href="/">
                <div class="card-official-stores-brand-overlay"></div>
                <div class="card-official-stores-brand-img">
                  <img src={spring} class="image"></img>
                </div>
                <div class="card-official-stores-shop-img">
                  <img src={hoamai1} class="image" height="70px"></img>
                </div>
                <div class="card-official-stores-h1" style={{'font-size': '24px','line-height':'26px'}}> Spring </div>
                <p class="card-official-stores-p"style={{'font-size': '16px','line-height':'18px'}}> mai, lan, dao, cuc, cam chuong... </p>
        </a>
        <a class="card-official-stores-box hp-mod-card-hover align-left" href="/">
                <div class="card-official-stores-brand-overlay"></div>
                <div class="card-official-stores-brand-img">
                  <img src={summer} class="image"></img>
                </div>
                <div class="card-official-stores-shop-img">
                  <img src={hoaphuong} class="image" height="70px"></img>
                </div>
                <div class="card-official-stores-h1" style={{'font-size': '24px','line-height':'26px'}}> Summer </div>
                <p class="card-official-stores-p"style={{'font-size': '16px','line-height':'18px'}}> phuong, giay,... </p>
        </a>
        <a class="card-official-stores-box hp-mod-card-hover align-left" href="/">
                <div class="card-official-stores-brand-overlay"></div>
                <div class="card-official-stores-brand-img">
                  <img src={autumn} class="image"></img>
                </div>
                <div class="card-official-stores-shop-img">
                  <img src={hoasua1} class="image" height="70px"></img>
                </div>
                <div class="card-official-stores-h1" style={{'font-size': '24px','line-height':'26px'}}> Fall </div>
                <p class="card-official-stores-p"style={{'font-size': '16px','line-height':'18px'}}> sua, da quy, thach thao,... </p>
        </a>
        <a class="card-official-stores-box hp-mod-card-hover align-left" href="/" style={{'margin-right': '0px'}}>
                <div class="card-official-stores-brand-overlay"></div>
                <div class="card-official-stores-brand-img">
                  <img src={winter} class="image"></img>
                </div>
                <div class="card-official-stores-shop-img">
                  <img src={hoacuchoami} class="image" height="70px"></img>
                </div>
                <div class="card-official-stores-h1" style={{'font-size': '24px','line-height':'26px'}}> Winter </div>
                <p class="card-official-stores-p"style={{'font-size': '16px','line-height':'18px'}}> cuc, hoa mi, cai trang, huong duong,... </p>
        </a>
      </div>
      </section>
    </div>
    </div>

    <div class="z-pzX _2K_le" >
    <div class="_3ZKbS _3Qi4X" data-spm="topics-list" style={{'background-color': 'white'}}>
    <div class="_3rNqU" data-spm="home-category">
    <ul class="_3HwoO">
      <li class="gmre0 column-4" data-ui="ui-topics-item">
                <a class="_2fR27" href="/helpcenter/Danh-cho-Khach-hang-moi/" data-spm="d_44810">
                  <span class="_3nNlm" data-ui="ui-topic-icon">
                    <img class="vu756" src={data} alt="New user"></img>
                  </span>
                  <span class="_1tFVN" data-ui="ui-topic-label">
                    <span class="_1-XD7">Data Privacy</span>
                    <span class="_2utOs"></span>
                  </span>
                </a>
      </li>
      <li class="gmre0 column-4" data-ui="ui-topics-item">
                <a class="_2fR27" href="/helpcenter/my-account/" data-spm="d_43825" data-spm-anchor-id="a2o4n.helpcenter.home-category.d_43825">
                  <span class="_3nNlm" data-ui="ui-topic-icon">
                    <img class="vu756" src={shipping} alt="My Account"></img>
                  </span>
                  <span class="_1tFVN" data-ui="ui-topic-label">
                    <span class="_1-XD7">Shipping & Delivery</span>
                    <span class="_2utOs">shipping and delivery</span>
                  </span>
                </a>
      </li>
      <li class="gmre0 column-4" data-ui="ui-topics-item">
                <a class="_2fR27" href="/helpcenter/my-account/" data-spm="d_43825" data-spm-anchor-id="a2o4n.helpcenter.home-category.d_43825">
                  <span class="_3nNlm" data-ui="ui-topic-icon">
                    <img class="vu756" src={refund} alt="My Account"></img>
                  </span>
                  <span class="_1tFVN" data-ui="ui-topic-label">
                    <span class="_1-XD7">Refunds</span>
      
                  </span>
                </a>
      </li>
      <li class="gmre0 column-4" data-ui="ui-topics-item">
                <a class="_2fR27" href="/helpcenter/my-account/" data-spm="d_43825" data-spm-anchor-id="a2o4n.helpcenter.home-category.d_43825">
                  <span class="_3nNlm" data-ui="ui-topic-icon">
                    <img class="vu756" src={return1} alt="My Account"></img>
                  </span>
                  <span class="_1tFVN" data-ui="ui-topic-label">
                    <span class="_1-XD7">Returns</span>
                    <span class="_2utOs">returns</span>
                  </span>
                </a>
      </li>
      <li class="gmre0 column-4" data-ui="ui-topics-item" style={{'margin-left':'20px'}}>
                <a class="_2fR27" href="/helpcenter/my-account/" data-spm="d_43825" data-spm-anchor-id="a2o4n.helpcenter.home-category.d_43825">
                  <span class="_3nNlm" data-ui="ui-topic-icon">
                    <img class="vu756" src={securepayment} alt="My Account"></img>
                  </span>
                  <span class="_1tFVN" data-ui="ui-topic-label">
                    <span class="_1-XD7">Secure Payments</span>
                    <span class="_2utOs"></span>
                  </span>
                </a>
      </li>
    </ul>
    <br></br>
    </div>

    </div>
    </div>


    <div class="daitutao " style={{'font-size':'35px','height':'70px',backgroundImage: `url(${feature_flower_banner})`,'font-weight':'500'}}>
        Featured Flowers
      </div>

    <div class="hp-mod-card hp-mod-bday-card card-fs J_FlashSale" id="hp-flash-sale" data-module-id="flashSale" data-spm="flashSale">
    <textarea style={{'display': 'none'}} class="J_FSData"></textarea>
    <textarea style={{'display': 'none'}} class="J_FSShopMore"></textarea>
    <div class="hp-mod-card-header"></div>
    <div class="card-fs-content" style={{'height': '550px','margin-bottom': '30px'}}>
    <div class="card-fs-content-header J_FSHeader" data-spm-anchor-id="a2o4n.home.flashSale.i0.30f2e182ktPC2s">
    <div class="card-fs-content-header-left J_FSHeaderLeft">
    <div class="card-fs-content-header-left-timer pull-left" style={{'margin-left':'0px'}}>
    <div class="fs-timer-count pull-left" id="hours" style={{'background-color': 'undefined', 'color': 'undefined'}}>02</div>
        <div class="fs-timer-colon pull-left" style={{'color': 'undefined'}}> : </div>
        <div class="fs-timer-count pull-left" id="minutes" style={{'background-color': 'undefined', 'color': 'undefined'}}>47</div>
        <div class="fs-timer-colon pull-left" style={{'color': 'undefined'}}> : </div>
        <div class="fs-timer-count pull-left" id="seconds" style={{'background-color': 'undefined', 'color': 'undefined'}}>12</div>

    </div>
    </div>
    <a class="card-fs-content-button J_ShopMoreBtn" title="Flash Sale" style={{'color': '#f57224', 'border-color': '#f57224'}} href="/">SHOP ALL PRODUCTS</a>
    </div>
    <div class="card-fs-content-body J_FSBody" title="Flash Sale">

    {
      products.slice(0,4).map((product,index)=>(


        <Link class="card-fs-content-body-unit hp-mod-card-hover J_FSItemUnit" to={`/products/${product.id}`}>
        <div class="fs-card-img-container">
          <img class="fs-card-img" src={product.image} alt="rose_bq_red"></img>
        </div>
  
        <div class="fs-card-text">
          <div class="fs-card-title">{product.name}</div>
          <div class="fs-card-price">
            <span class="currency">$</span><span class="price">{product.price}</span>
          </div>
          <div class="fs-card-origin-price">
            <span class="fs-origin-price">
              <span class="currency">₫</span><span class="price">398000</span>
            </span>
            <span class="fs-discount">
                         -57%
            </span>
          </div>
          <div class="fs-card-sold">
                      
          </div>
        </div>
      </Link>

      ))
    }
    
    </div>
    </div>

    </div>




    <Footer/>
    
    </body>
  )
}
