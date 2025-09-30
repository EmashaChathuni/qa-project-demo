import React from "react";
import { Link } from "react-router-dom";

const NavigationBar = () => {
  return (
    <nav style={{ backgroundColor: "#333", padding: "1rem" }}>
      <div style={{ display: "flex", justifyContent: "space-between" }}>
        <Link to="/" style={{ color: "white", textDecoration: "none" }}>
          Recipe App
        </Link>
        <div>
          <Link to="/login" style={{ color: "white", marginRight: "10px" }}>
            Login
          </Link>
          <Link to="/signup" style={{ color: "white" }}>
            Signup
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default NavigationBar;