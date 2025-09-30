# TDD Demonstration Guide & Screenshots
# Test-Driven Development: Red-Green-Refactor Cycle

## 🎯 CORE FEATURES TO TEST:
1. **Add Recipe** - Core business logic
2. **Validate User Input** - Input validation logic

---

## 📋 TDD DEMONSTRATION STEPS

### STEP 1: RED PHASE - Write Failing Tests
**What to show:** Tests failing because implementation is not yet written

**Screenshot Points:**
- IDE with test file open
- Run tests showing RED (failing) status
- Test output showing failures

### STEP 2: GREEN PHASE - Minimum Implementation
**What to show:** Write just enough code to make tests pass

**Screenshot Points:**
- IDE showing minimal implementation
- Run tests showing GREEN (passing) status
- Test results showing all tests pass

### STEP 3: REFACTOR PHASE - Improve Code Quality
**What to show:** Improve code while keeping tests green

**Screenshot Points:**
- IDE showing refactored code
- Tests still GREEN after refactoring
- Code quality improvements

---

## 🖥️ SCREENSHOT CHECKLIST FOR PRESENTATION:

### IDE Setup Screenshots:
☐ VS Code with project structure visible
☐ Test file (RecipeServiceTest.java) open
☐ Service file (RecipeService.java) open
☐ Terminal showing test execution

### RED Phase Screenshots:
☐ Tests failing (red status in terminal)
☐ Error messages showing "not implemented"
☐ Test runner output showing failed tests

### GREEN Phase Screenshots:
☐ Minimal implementation in RecipeService.java
☐ Tests passing (green status)
☐ Test runner showing all tests passed

### REFACTOR Phase Screenshots:
☐ Improved code implementation
☐ Tests still passing after refactor  
☐ Code showing better structure/readability

**✅ COMPLETED: calculateAverageRating method refactored with:**
- Input validation (null/negative ID checks)
- Mock ratings calculation logic
- Proper error handling
- 13 total tests passing (added 2 validation tests)

---

## 🔧 COMMANDS TO RUN:

1. **Run All Tests:**
   ```bash
   cd backend
   mvn test
   ```

2. **Run Specific Test Class:**
   ```bash
   mvn test -Dtest=RecipeServiceTest
   ```

3. **PowerShell (Windows) - Use this for clean output:**
   ```bash
   .\mvnw.cmd test -Dtest=RecipeServiceTest
   ```

---

## 📊 FINAL RESULTS - TDD CYCLE COMPLETED:

### RED Phase ✅ COMPLETED:
- ❌ Tests failing - method not implemented
- Error: "Cannot resolve method calculateAverageRating" 
- Build failed with compilation error

### GREEN Phase ✅ COMPLETED:
- ✅ Minimal implementation added
- Return hardcoded 4.5 to pass test
- 11 tests passing

### REFACTOR Phase ✅ COMPLETED:
- ✅ Enhanced with realistic implementation
- Added input validation and mock data
- Added 2 additional validation tests
- **Final result: 13 tests passing, 0 failures**

---

## 🎥 LIVE DEMONSTRATION FLOW:

1. **Show failing tests (RED)**
   - Open RecipeServiceTest.java
   - Run tests: `mvn test -Dtest=RecipeServiceTest`
   - Screenshot: Red failures

2. **Implement minimum code (GREEN)**
   - Open RecipeService.java
   - Add basic implementation
   - Run tests again
   - Screenshot: Green success

3. **Refactor code (REFACTOR)**
   - Improve implementation
   - Run tests to ensure still green
   - Screenshot: Still green after refactor

4. **Show test coverage**
   - Demonstrate comprehensive testing
   - Show different test scenarios