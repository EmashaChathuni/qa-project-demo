import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const AddRecipe = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    try {
      const response = await fetch("http://localhost:8080/api/recipes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ title, description }),
      });

      const data = await response.json();

      if (response.ok) {
        setSuccess("Recipe added successfully!");
        setTitle("");
        setDescription("");
        setTimeout(() => {
          navigate("/");
        }, 2000);
      } else {
        setError(data.message || "Failed to add recipe");
      }
    } catch (error) {
      setError("Network error. Please try again.");
    }
  };

  return (
    <div style={{ 
      maxWidth: "500px", 
      margin: "50px auto", 
      padding: "20px",
      backgroundColor: "white",
      borderRadius: "8px",
      boxShadow: "0 2px 10px rgba(0,0,0,0.1)"
    }}>
      <h2 style={{ textAlign: "center", marginBottom: "30px" }}>Add New Recipe</h2>
      
      {error && (
        <div style={{ 
          color: "red", 
          textAlign: "center", 
          marginBottom: "20px",
          padding: "10px",
          backgroundColor: "#ffebee",
          borderRadius: "4px"
        }}>
          {error}
        </div>
      )}

      {success && (
        <div style={{ 
          color: "green", 
          textAlign: "center", 
          marginBottom: "20px",
          padding: "10px",
          backgroundColor: "#e8f5e8",
          borderRadius: "4px"
        }}>
          {success}
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "15px" }}>
          <label style={{ display: "block", marginBottom: "5px", fontWeight: "bold" }}>
            Recipe Title:
          </label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            style={{
              width: "100%",
              padding: "10px",
              border: "1px solid #ddd",
              borderRadius: "4px",
              fontSize: "16px"
            }}
            placeholder="Enter recipe title..."
            required
          />
        </div>

        <div style={{ marginBottom: "20px" }}>
          <label style={{ display: "block", marginBottom: "5px", fontWeight: "bold" }}>
            Description:
          </label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            style={{
              width: "100%",
              padding: "10px",
              border: "1px solid #ddd",
              borderRadius: "4px",
              fontSize: "16px",
              minHeight: "100px",
              resize: "vertical"
            }}
            placeholder="Enter recipe description..."
          />
        </div>

        <button
          type="submit"
          style={{
            width: "100%",
            padding: "12px",
            backgroundColor: "#17a2b8",
            color: "white",
            border: "none",
            borderRadius: "4px",
            fontSize: "16px",
            cursor: "pointer"
          }}
        >
          Add Recipe
        </button>
      </form>

      <p style={{ textAlign: "center", marginTop: "20px" }}>
        <Link to="/" style={{ color: "#007bff" }}>Back to Home</Link>
      </p>
    </div>
  );
};

export default AddRecipe;
