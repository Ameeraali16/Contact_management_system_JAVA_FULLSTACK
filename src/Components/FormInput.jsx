import React from 'react';

const FormInput = ({ type, placeholder, value, onChange, name }) => (
  <input
    type={type}
    placeholder={placeholder}
    value={value}
    onChange={onChange}
    name={name}
    style={{
      width: '100%',
      height: '40px',
      marginBottom: '15px',
      padding: '0 10px',
      borderRadius: '5px',
      fontFamily: 'Montserrat',
      fontSize: '0.9rem'
    }}
  />
);

export default FormInput;
