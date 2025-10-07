# GitHub Issues Setup Guide

## How to Create GitHub Issues for Your Demonstration

### **Step 1: Navigate to Your Repository**
1. Go to: https://github.com/EmashaChathuni/qa-project-demo
2. Click on the **"Issues"** tab
3. Click **"New Issue"** button

---

## **Issue Template 1: Critical Security Bug**

### **Title**: 
```
[CRITICAL] Authentication bypass vulnerability in login endpoint
```

### **Labels**: 
- `bug`
- `critical`
- `security`
- `authentication`

### **Description**:
```markdown
## 🚨 Critical Security Issue

**Bug ID**: QA-001  
**Severity**: Critical  
**Component**: Authentication Controller  

### Description
The `/api/auth/login` endpoint incorrectly processes authentication requests with null or empty usernames, potentially allowing unauthorized access.

### Environment
- **Version**: v1.0.0
- **Java**: 21.0.8
- **Framework**: Spring Boot 3.5.6

### Steps to Reproduce
1. Start the Spring Boot application
2. Send POST request to `http://localhost:8080/api/auth/login`
3. Include request body:
   ```json
   {
     "username": "",
     "password": "anypassword"
   }
   ```
4. Observe the response processes empty username

### Expected vs Actual
- **Expected**: HTTP 400 with clear error message
- **Actual**: Application processes empty username and returns generic error

### Root Cause
Missing null/empty validation in `AuthController.java` at line 26-33

### Security Impact
- Potential authentication bypass
- Information disclosure through error messages
- No security audit logging

### Priority
🔴 **CRITICAL** - Requires immediate attention
```

---

## **Issue Template 2: Performance Bug**

### **Title**: 
```
[MAJOR] Database connection pool exhaustion under load
```

### **Labels**: 
- `bug`
- `major`
- `performance`
- `database`

### **Description**:
```markdown
## 📊 Performance Issue

**Bug ID**: QA-002  
**Severity**: Major  
**Component**: Database Layer  

### Description
Application fails under concurrent load due to SQLite connection limitations and insufficient connection pool configuration.

### Performance Test Results
- **Tool**: Apache JMeter 5.6.3
- **Load**: 10 concurrent users, 5 loops each
- **Result**: 15-25% error rate, response times >2000ms

### Error Logs
```
HikariPool-1 - Connection is not available, request timed out after 30000ms
org.springframework.dao.DataAccessResourceFailureException
```

### Root Cause Analysis
1. SQLite single-writer limitation
2. Default HikariCP configuration insufficient
3. No connection pool monitoring

### Proposed Solution
- Immediate: Increase connection pool settings
- Long-term: Migrate to PostgreSQL/MySQL
- Monitoring: Add connection pool metrics

### Impact
🟡 **MAJOR** - Affects application scalability
```

---

## **Issue Template 3: UI/UX Bug**

### **Title**: 
```
[MINOR] Poor form validation UX in recipe creation
```

### **Labels**: 
- `bug`
- `minor`
- `ui/ux`
- `frontend`

### **Description**:
```markdown
## 🎨 UI/UX Issue

**Bug ID**: QA-003  
**Severity**: Minor  
**Component**: Frontend Forms  

### Description
Recipe creation form lacks proper validation feedback and user experience enhancements.

### Issues Identified
- No real-time validation
- Generic error messages
- No field highlighting
- Poor accessibility support

### Steps to Reproduce
1. Navigate to Add Recipe page
2. Submit empty form
3. Observe poor error handling

### User Impact
- Confusing error messages
- No immediate feedback
- Accessibility concerns

### Priority
🟢 **MINOR** - Enhancement for better UX
```
```