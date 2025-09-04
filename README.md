# HelloRestAPI 🚀

A Spring Boot REST API project with MySQL database and CI/CD pipeline.

## 📋 Features

- **REST API Endpoints** for CRUD operations
- **MySQL Database** integration with JPA/Hibernate
- **Spring Security** configuration
- **Docker** support for containerization
- **CI/CD Pipeline** with GitHub Actions
- **Comprehensive Testing** with JUnit and MockMvc

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **Spring Security**
- **MySQL 8.0**
- **Maven**
- **Docker & Docker Compose**
- **GitHub Actions**

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Docker (optional)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/shashiprabha772/HelloRestAPI.git
   cd HelloRestAPI
   ```

2. **Setup MySQL Database**
   ```sql
   CREATE DATABASE HelloRestAPI;
   ```

3. **Configure application.properties**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/HelloRestAPI
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

### Using Docker Compose

1. **Start all services**
   ```bash
   docker-compose up -d
   ```

2. **Access the application**
   - API: http://localhost:8081
   - MySQL: localhost:3306

## 📚 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/hello` | Simple hello message |
| GET | `/api/hello/greet/{name}` | Personalized greeting |
| GET | `/api/hello/status` | API status check |
| POST | `/api/hello/messages` | Create new message |
| GET | `/api/hello/message` | Get all messages |
| GET | `/api/hello/messages/{id}` | Get message by ID |
| GET | `/api/hello/messages/sender/{sender}` | Get messages by sender |
| PUT | `/api/hello/messages/{id}` | Update message |
| DELETE | `/api/hello/message/{id}` | Delete message |

### Example API Calls

```bash
# Get simple hello
curl http://localhost:8081/api/hello

# Create a new message
curl -X POST http://localhost:8081/api/hello/messages \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello World!", "sender": "John Doe"}'

# Get all messages
curl http://localhost:8081/api/hello/message
```

## 🧪 Testing

Run tests with Maven:
```bash
./mvnw test
```

Run tests with coverage:
```bash
./mvnw test jacoco:report
```

## 🔄 CI/CD Pipeline

This project uses **GitHub Actions** for automated CI/CD:

### CI Pipeline Features:
- ✅ **Automated Testing** on every push/PR
- ✅ **Multi-environment Testing** with MySQL and H2
- ✅ **Build Verification** 
- ✅ **Artifact Generation**
- ✅ **Docker Image Building**

### Triggers:
- **Push** to `main` or `develop` branches
- **Pull Requests** to `main` branch
- **Manual Dispatch** for deployments

### Pipeline Status:
![CI](https://github.com/shashiprabha772/HelloRestAPI/workflows/CI%2FCD%20Pipeline/badge.svg)

## 🐳 Docker

### Build Docker Image
```bash
docker build -t hellorestapiapp:latest .
```

### Run with Docker
```bash
docker run -p 8081:8081 hellorestapiapp:latest
```

## 📝 Project Structure

```
HelloRestAPI/
├── .github/workflows/       # CI/CD pipeline configurations
├── src/main/java/
│   └── com/example/HelloRestAPI/
│       ├── controller/      # REST controllers
│       ├── config/         # Configuration classes
│       └── HelloRestApiApplication.java
├── src/main/resources/
│   └── application.properties
├── src/test/               # Test classes
├── docker-compose.yml      # Docker Compose configuration
├── Dockerfile             # Docker image configuration
└── pom.xml               # Maven dependencies
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👤 Author

**Shashi Prabha**
- GitHub: [@shashiprabha772](https://github.com/shashiprabha772)
- Email: shashiprabha772@gmail.com
