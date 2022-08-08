import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import './App.css';
import HomePage from './pages/HomePage';
import NavBar from './components/NavBar';
import MockAttractionPage from './pages/MockAttractionPage';
import AttractionPage from './pages/AttractionPage';

const App: React.FC = () => {

    return (
        <div>
            <NavBar></NavBar>
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<HomePage />} />
                    <Route path='/attractions' element={<AttractionPage />} />
                    <Route path='/mockattractions' element={<MockAttractionPage />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
