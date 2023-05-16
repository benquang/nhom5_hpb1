import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route, useParams } from 'react-router-dom';
import Home from './body/Home';
import Shop from './body/Shop';
import Cart from './body/Cart';
import Order from './body/Order';
import Profile from './body/Profile';
import Login from './body/Login'
import ProductDetail from './body/ProductDetail';

function App() {
  return (
    <div className="App">
      <Router>

       <Routes>
        <Route exact path="/" element = {<Home/>}></Route>
        <Route exact path="/shop" element = {<Shop/>}></Route>
        <Route exact path="/cart" element = {<Cart/>}></Route>
        <Route exact path="/order" element = {<Order/>}></Route>
        <Route exact path="/profile" element = {<Profile/>}></Route>
        <Route exact path="/login" element = {<Login/>}></Route>
        <Route exact path='/login/:message' element = {<Login/>}></Route>
        <Route exact path='/products/:id' element = {<ProductDetail/>}></Route>

       </Routes>

     </Router>
    </div>
  );
}

export default App;
