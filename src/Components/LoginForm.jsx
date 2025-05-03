import React, { useState } from 'react';
import FormInput from './FormInput';
import { useNavigate } from "react-router-dom";
import { useAuth } from "../Context/AuthContext";  // Make sure this is correct

const LoginForm = () => {
  const navigate = useNavigate();
  const { login } = useAuth(); // ✅ Get login function from context

  const [formData, setFormData] = useState({
    identifier: "",
    password: ""
  });

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();

    // MOCK login logic
    if (formData.identifier && formData.password) {
      const mockToken = "mockToken123";

      // ✅ Call login to update context AND localStorage
      login(mockToken);

      // ✅ Redirect after setting user
      navigate("/ContactPage");
    } else {
      alert("Enter valid credentials.");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <h2>Login</h2>
      <FormInput
        type="text"
        name="identifier"
        placeholder="Email or Phone"
        value={formData.identifier}
        onChange={handleChange}
      />

      <FormInput
        type="password"
        name="password"
        placeholder="Password"
        value={formData.password}
        onChange={handleChange}
      />
      
      <button type="submit" className="submit-btn">Login</button>
    </form>
  );
};

export default LoginForm;
