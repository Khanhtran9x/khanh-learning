import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './component/Home';
import Header from './component/Header';
import Create from './component/Create';
import Update from './component/Update';

export default function App() {
  return (
    <BrowserRouter>
      <Header></Header>
      <Routes>
        <Route index element={<Home />} />
        <Route path="create" element={<Create />} />
        <Route path="update" element={<Update />} />
      </Routes>
    </BrowserRouter>
  );
};
