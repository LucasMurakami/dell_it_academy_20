import BetCards from "./dataComponent/BetCards";
import BetCardPage from "./pages/BetCardPage";
import EditionPage from "./pages/EditionPage";
import Editions from "./dataComponent/Editions"

import Footer from "./components/Footer"
import Home from "./pages/HomePage";
import Nav from "./components/Nav"
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LuckyClients from "./dataComponent/LuckyClients";

// Arquivo que configura os endpoints do frontend.

function App() {

  return (    
    <>    
      <BrowserRouter>
          <Nav/>
          <section>
            <Routes>
              {/* // http://localhost:8080 */}
              <Route path="/" element={ <Home /> } />

              {/* // http://localhost:8080/editions */}
              <Route path="/editions" element={ <Editions/> } />

              {/* // http://localhost:8080/add-betcard */}
              <Route path="/add-betcard" element={ <BetCardPage/>}/>

              {/* // http://localhost:8080/edition */}
              <Route path="/edition" element={ <EditionPage /> }/>

              {/* // http://localhost:8080/cardbets */}
              <Route path="/betcards" element={ <BetCards/> }/>

              <Route path="/luckyclients" element={ <LuckyClients/> }/>
            </Routes>
          </section>
          <section>
            <Footer/>
          </section>    
        </BrowserRouter> 
     </>
  )
}

export default App
