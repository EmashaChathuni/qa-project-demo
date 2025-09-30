# 🔴 RED PHASE SCREENSHOTS - SEPARATED BY OPERATION

## **Screenshot 1: DELETE Operation RED Phase**

### Current Status: ✅ DELETE-ONLY RED PHASE READY

**What you have:**
- DELETE tests are active (uncommented)
- LOGIN tests are commented out
- Error Message: `"The method deleteRecipe(Long) is undefined for the type RecipeService"`
- Build Status: **BUILD FAILURE**
- Test Results: **Tests run: 16, Failures: 0, Errors: 16**

### **Screenshot Instructions for DELETE RED PHASE:**
1. **Show IDE** with `RecipeServiceTest.java` open showing DELETE tests
2. **Run command:** `.\mvnw.cmd test -Dtest=RecipeServiceTest`
3. **Capture terminal** showing "method deleteRecipe(Long) is undefined" errors
4. **Show RecipeService.java** - missing `deleteRecipe()` method

---

## **Screenshot 2: LOGIN Operation RED Phase** 

### Next Step: LOGIN-ONLY RED PHASE

**What you need to do:**
1. Comment out DELETE tests 
2. Uncomment LOGIN tests
3. Run tests to show LOGIN failures

**Expected Results:**
- Error Message: `"The method authenticateUser(String, String) is undefined for the type UserService"`
- Build Status: **BUILD FAILURE**
- Clean focus on LOGIN operation only

### **Commands for LOGIN RED PHASE:**
```bash
# After modifying test file to show only LOGIN tests:
.\mvnw.cmd test -Dtest=RecipeServiceTest
```

---

## **Screenshot 3: COMBINED RED Phase**

### Final Step: BOTH DELETE + LOGIN RED PHASE

**What you need to do:**
1. Uncomment both DELETE and LOGIN tests
2. Run tests to show both failures

**Expected Results:**
- Error Messages for both `deleteRecipe()` AND `authenticateUser()` methods
- Build Status: **BUILD FAILURE**  
- Shows complete RED phase for both operations

---

## 📋 **Quick Toggle Instructions:**

### For DELETE-Only Screenshots:
- Keep DELETE tests uncommented
- Comment LOGIN tests with `/* */`

### For LOGIN-Only Screenshots:  
- Comment DELETE tests with `/* */`
- Keep LOGIN tests uncommented

### For Combined Screenshots:
- Uncomment both DELETE and LOGIN tests
- Show all 6 errors together

**You currently have DELETE-ONLY RED PHASE ready for screenshots!**