import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import './App.css';
import { attraction } from './interfaces';
import HomePage from './pages/HomePage';
import NavBar from './components/NavBar';

const App: React.FC = () => {

  const testMap: attraction = { name: "test", cost: 123123, type: "DESSERT" }
  const testMap2: attraction = {
    name: "test", cost: 123123, type: "DESSERT", photoId: "asdads", locations: [{
      address: "String", nearestMRT: "String", latitude: 1.12,
      longitude: 1.45
    }], description: "Test Description", readMore: "https://google.com"
  }

  return (
    // <div className="App">
    //   Hello World
    //   <AttractionInfo isMock={true} payload={testMap} ></AttractionInfo>
    //   <AttractionInfo isMock={false} payload={testMap2} ></AttractionInfo>
    // </div>
    <div>
      <NavBar></NavBar>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<HomePage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
