// /src/pages/Home.jsx
import React from "react";
import Header from "../Components/Header";
import Feature from "../Components/Feature";
import About from "../Components/About";
import image from "../images/Frame 19.png";

function Home() {
  return (
    <>
      <Header />
      
      {/* Add id to this section for navigation */}
      <div id="features-section">
        <Feature />
      </div>
      
      {/* Add id to this section for navigation */}
      <div id="about-section">
        <About
          image={image}
          title="About ConTANK"
          button="Sign Up Now!"
        />
      </div>
    </>
  );
}

export default Home;