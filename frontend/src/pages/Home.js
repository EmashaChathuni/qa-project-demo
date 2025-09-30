import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div style={{ textAlign: "center", padding: "40px" }}>
      <h1>Welcome to Recipe App</h1>
      <p>Simple recipe management system</p>
      <div style={{ marginTop: "30px" }}>
        <Link to="/login" style={{ 
          padding: "10px 20px", 
          margin: "10px",
          backgroundColor: "#007bff", 
          color: "white", 
          textDecoration: "none",
          borderRadius: "4px",
          display: "inline-block"
        }}>
          Login
        </Link>
        <Link to="/signup" style={{ 
          padding: "10px 20px", 
          margin: "10px",
          backgroundColor: "#28a745", 
          color: "white", 
          textDecoration: "none",
          borderRadius: "4px",
          display: "inline-block"
        }}>
          Sign Up
        </Link>
        <Link to="/add-recipe" style={{ 
          padding: "10px 20px", 
          margin: "10px",
          backgroundColor: "#17a2b8", 
          color: "white", 
          textDecoration: "none",
          borderRadius: "4px",
          display: "inline-block"
        }}>
          Add Recipe
        </Link>
      </div>
    </div>
  );
};

export default Home;