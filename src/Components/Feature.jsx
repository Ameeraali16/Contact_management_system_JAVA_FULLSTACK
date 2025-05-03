import React from "react";
import FeatureBox from './FeatureBox'
import featureimage from '../images/feature_1.png'
import featureimage1 from '../images/feature_2.png'
import featureimage2 from '../images/feature_3.png'

function Feature() {

    console.log("Imported image:", featureimage);

    return(
        
        <div id='features'>
            <div className="a-container">
                <FeatureBox image={featureimage} title='Pagination' subtitle='Navigate through pages with ease.'/>
                <FeatureBox image={featureimage1} title='Notes' subtitle='Add personal notes to each contact.'/>
                <FeatureBox image={featureimage2} title='Reminders' subtitle='Set and receive timely reminders.' />
            </div>
        </div>
    )
}
export default Feature;