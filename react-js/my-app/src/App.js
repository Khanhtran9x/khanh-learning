import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from 'react-router-dom';
import Home from './component/Home';
import Cart from './component/Cart';

export default function App() {
  return (
    <Router>
      <Routes>
          <Route index element={<Home />} />
          <Route path="carts" element={<Cart />} />
      </Routes>
    </Router>
  );
};
