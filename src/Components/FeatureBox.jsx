import React from "react";

function FeatureBox(props) {
    console.log("Image prop received:", props.image); 
    return (
        <div className="a-box">
            <div className="a-b-img">
                <img src={props.image} alt="" />
            </div>
            <div className="s-b-text">
                <h2>{props.title}</h2> 
                <p>{props.subtitle}</p> {/* ← use subtitle prop here */}
            </div>
        </div>
    );
}

export default FeatureBox;
