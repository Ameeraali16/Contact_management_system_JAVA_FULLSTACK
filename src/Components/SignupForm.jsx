import React, { useState } from 'react';
import FormInput from './FormInput';

const SignupForm = () => {
    const [formData, setFormData] = useState({
        identifier: "",
        password: ""
      });
      

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Signup Data:', formData);
    // Submit logic here
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <h2 >Sign Up</h2>
      <FormInput type="text" name="name" placeholder="First Name" value={formData.name} onChange={handleChange} />
      <FormInput type="text" name="name" placeholder="Last Name" value={formData.name} onChange={handleChange} />
      <FormInput
  type="text"
  name="identifier"
  placeholder="Email or Phone"
  value={formData.identifier}
  onChange={handleChange}
/>

      <FormInput type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} />
      <button type="submit" className="submit-btn">Register</button>
    </form>
  );
};

export default SignupForm;
