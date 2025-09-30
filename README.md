# Food Recipe Web Application

Simple web application with separate backend and frontend folders.

## Project Structure
```
qa/
├── backend/          # Spring Boot backend
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
├── frontend/         # React frontend  
│   ├── src/
│   ├── package.json
│   └── public/
└── README.md
```

## Backend (Spring Boot)

### Running the Backend
1. Navigate to backend directory:
   ```bash
   cd backend
   ```
2. Run the application:
   ```bash
   mvnw.cmd spring-boot:run
   ```
3. Backend starts on http://localhost:8080

### API Endpoints
- POST /api/auth/login - User login
- POST /api/auth/signup - User registration  
- GET /api/recipes - Get all recipes
- POST /api/recipes - Create new recipe

## Frontend (React)

### Running the Frontend
1. Navigate to frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start development server:
   ```bash
   npm start
   ```
4. Frontend opens at http://localhost:3000

### Pages
- Home (/) - Welcome page
- Login (/login) - User login form
- Signup (/signup) - User registration form  
- Recipes (/recipes) - Browse recipes
- Create Recipe (/create-recipe) - Create new recipe

## Quick Start
1. Start backend: `cd backend && mvnw.cmd spring-boot:run`
2. Start frontend: `cd frontend && npm start`
3. Open http://localhost:3000

## Features
- Simple login/signup
- Home page with navigation
- Recipe CRUD operations
- Bootstrap styling
- H2 in-memory database