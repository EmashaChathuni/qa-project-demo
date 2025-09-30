import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Auth services
export const authService = {
  login: (credentials) => {
    return api.post('/login', credentials);
  },

  signup: (userData) => {
    return api.post('/signup', userData);
  },
};

// Simple recipe service (if needed)
export const recipeService = {
  addRecipe: (recipeData) => {
    return api.post('/recipes', recipeData);
  },
};

export default api;