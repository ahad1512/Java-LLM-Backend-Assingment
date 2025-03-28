# PDF Reader API using LLM (Java + Spring Boot)

## 🚀 Overview
This project is a **Java-based REST API** built with **Spring Boot** that extracts relevant information from a **CASA bank statement PDF** using **Apache PDFBox** and **OpenAI's LLM API (GPT-4)**.

### ✨ Features
- 📄 **Parse CASA bank statements** to extract Name, Email, Opening Balance, and Closing Balance.
- 🤖 **Uses OpenAI API (GPT-4)** to intelligently extract data.
- 📂 Accepts **file uploads** via REST API.
- 🔑 **Bonus:** Generates a **dynamic password** based on the user's name and date of birth.
- 🖥️ Deployed as a REST API, accessible via **Postman, cURL, or any HTTP client**.

---

## 🏗️ Project Structure
```
pdf-reader-api/
│── src/main/java/com/example/pdfreader/
│   ├── controller/         # API Controllers
│   ├── service/            # Business Logic
│   ├── utils/              # Helper Classes (PDF Parsing, LLM Calls, Password Generation)
│   ├── model/              # Data Models
│── resources/
│   ├── application.yml     # Configuration (API keys, Server settings)
│── README.md
│── pom.xml                # Maven dependencies
│── Dockerfile (optional)
```

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-repo/pdf-reader-api.git
cd pdf-reader-api
```

### 2️⃣ Set OpenAI API Key
Edit `src/main/resources/application.yml` and add your OpenAI API key:
```yaml
openai:
  api:
    key: YOUR_OPENAI_API_KEY
```

### 3️⃣ Build & Run
#### 🔧 Using Maven
```bash
mvn clean install
mvn spring-boot:run
```
#### 🐳 Using Docker (Optional)
```bash
docker build -t pdf-reader-api .
docker run -p 8080:8080 pdf-reader-api
```

---

## 📡 API Endpoints

### 1️⃣ Upload a PDF & Extract Data
#### **Request:**
```bash
curl -X POST -F "file=@statement.pdf" \
     -F "firstName=Ahad" -F "dob=1995-06-21" \
     http://localhost:8080/api/pdf/upload
```
#### **Response:**
```json
{
    "name": "Ahad Rahmani",
    "email": "ahad@example.com",
    "openingBalance": "₹50,000",
    "closingBalance": "₹65,000",
    "password": "Aha19950621XyZ9"
}
```

---

## 📦 Dependencies

Add these to `pom.xml`:
```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Apache PDFBox for PDF Parsing -->
    <dependency>
        <groupId>org.apache.pdfbox</groupId>
        <artifactId>pdfbox</artifactId>
        <version>2.0.27</version>
    </dependency>
    
    <!-- JSON Processing (For OpenAI API Integration) -->
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20240303</version>
    </dependency>
</dependencies>
```

---

## 🛠️ Future Enhancements
- 📌 Add **password-protected** PDF support.
- 📌 Support for multiple pages in PDFs.
- 📌 Deploy API on **AWS/GCP**.

---

## 📜 License
MIT License. Contributions welcome! 🚀

