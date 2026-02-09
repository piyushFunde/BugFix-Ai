#  BugFix AI – Intelligent Bug Analysis System 

BugFix AI is an **AI-powered backend analysis tool** designed to automatically analyze software bugs, identify root causes, and suggest reliable fixes using **Generative AI (Google Gemini / OpenAI)**. The system is built to simulate **real-world enterprise debugging workflows**, making it suitable for production demos, portfolios, and interview discussions.

🌐 **Website:** [[[BugFix-AI]]](https://https://bugfix-ai-backend-odli.onrender.com/)

---
#🖼️ Screenshots
<img width="1916" height="821" alt="Screenshot 2026-02-06 213756" src="https://github.com/user-attachments/assets/74518bfc-3aad-411b-be86-664a4986c974" />
<img width="1887" height="832" alt="Screenshot 2026-02-06 225155" src="https://github.com/user-attachments/assets/9f425c84-9231-4dc4-a318-30eaad487b80" />


##  Problem Statement
In modern software teams:

* Bug reports are often **unclear, incomplete, or unstructured**
* Developers spend significant time understanding **root causes** before fixing
* Junior developers struggle to debug **runtime and logical issues** efficiently

 **BugFix AI solves this by using AI to analyze bug descriptions or stack traces and return:**

* Clear root cause analysis
* Actionable fix recommendations
* Code-level suggestions

---

##  Solution Overview

BugFix AI acts as an **AI-assisted debugging layer** between bug reports and developers.

### What it does:

1. Accepts a bug description or error trace
2. Sends the context to a Generative AI model (Gemini / OpenAI)
3. Parses and structures the AI response
4. Displays:

   * Root Cause
   * Recommended Fix
   * Code Snippet (when available)

---

##  System Architecture

```
Frontend (HTML + JS)
        |
        v
Spring Boot REST API (/api/analyze)
        |
        v
BugAnalysisService
        |
        v
AiClient (Strategy Pattern)
   |        |        |
Gemini   OpenAI   Mock Client
   |
   v
Generative AI API
```

### Architecture Highlights

* **Clean separation of concerns**
* **Strategy Pattern** for AI provider switching
* **Fail-safe parsing** (handles non-JSON AI responses)
* **Enterprise-style service + controller layering**

---

##  How It Works (Flow)

1. User enters a bug description in the web UI
2. Frontend sends a POST request to `/api/analyze`
3. `ApiAnalyzeController` forwards the request
4. `BugAnalysisServiceImpl` invokes the configured `AiClient`
5. AI generates structured analysis
6. Response is parsed and returned to UI
7. UI displays Root Cause and Fix in real-time

---

##  Tech Stack

### Backend

* **Java 17**
* **Spring Boot**
* **Spring Web (REST API)**
* **Jackson (JSON parsing)**
* **Lombok**

### AI Integration

* **Google Gemini API** (primary)
* **OpenAI API** (optional / pluggable)
* Prompt-engineered structured responses

### Frontend

* **HTML5**
* **CSS3 (Modern UI, Dark Theme)**
* **Vanilla JavaScript (Fetch API)**

### Tools & Platforms

* Maven
* IntelliJ IDEA
* Git & GitHub
* Postman / Thunder Client

---

##  AI Client Design (Strategy Pattern)

```java
public interface AiClient {
    AiBugAnalysisResponse analyzeBug(String description);
}
```

### Implementations

* `GeminiAiClient` → Real Gemini API
* `OpenAiClient` → OpenAI (optional)
* `GeminiMockClient` → Local testing / fallback

✔ Easily switch AI providers using `@Qualifier`

---

##  API Endpoint

### Analyze Bug

```
POST /api/analyze
Content-Type: application/json

{
  "bugDescription": "NullPointerException occurs when user is null"
}
```

### Response

```json
{
  "rootCause": "User object is accessed without null check",
  "fixExplanation": "Add validation before accessing user methods",
  "codeSnippet": "if(user == null) { throw new Exception(); }"
}
```

---

##  UI Features

* Clean, modern dark-themed UI
* Real-time loading feedback
* Structured AI output display
* User-friendly bug input form

---

##  Sample Bugs You Can Test

* `NullPointerException due to missing null checks`
* `LazyInitializationException in Hibernate`
* `REST API returns 500 when request body is invalid`
* `Race condition during concurrent updates`
* `N+1 query performance issue`

---

##  Why This Project Is Valuable

✔ Demonstrates **AI integration in backend systems**
✔ Shows **real-world debugging workflows**
✔ Uses **enterprise design patterns**
✔ Strong portfolio project for **Backend / Java / AI roles**
✔ Interview-ready explanation

---

##  Future Enhancements

* Bug severity classification
* Confidence score for AI analysis
* Jira ticket auto-comment integration
* Unit test generation
* Dockerized deployment
* Authentication & role-based access

---

##  Author

**Piyush Funde**
Computer Science Engineer | Backend Developer | AI Enthusiast

 GitHub: [https://github.com/](https://github.com/)<your-username>
 Portfolio: [https://piyushfunde-portfolio.vercel.app/](https://piyushfunde-portfolio.vercel.app/)









