# 📋 Task List App

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.14-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

A modern, feature-rich task management application built with Spring Boot, Thymeleaf, and Bootstrap. Perfect for personal productivity and team task management.

![Task List App Screenshot](https://via.placeholder.com/800x400/4f46e5/ffffff?text=Task+List+App+Demo)

## ✨ Features

- ✅ **Complete Task Management**: Create, edit, delete, and toggle task completion
- 🎯 **Smart Priority System**: Organize tasks by High, Medium, and Low priority with color coding
- 🔍 **Advanced Search & Filter**: Search tasks by keywords and filter by status/priority
- 📊 **Real-time Statistics**: View task completion statistics with animated progress visualization
- 📅 **Due Date Management**: Set and track task deadlines with overdue highlighting
- 🎨 **Modern UI/UX**: Beautiful, responsive design with smooth animations and transitions
- ⌨️ **Keyboard Shortcuts**: Quick actions (Ctrl+N for new task, Escape to close modals)
- 💾 **Auto-save Drafts**: Automatic draft saving for edit forms to prevent data loss
- 📱 **Mobile Responsive**: Optimized for desktop, tablet, and mobile devices
- 🌙 **Clean Interface**: Intuitive sidebar navigation with statistics overview

## 🛠️ Technology Stack

- **Backend**: Spring Boot 2.7.14, Spring Data JPA, Spring Web
- **Frontend**: Thymeleaf, Bootstrap 5.1.3, Font Awesome 6.0.0
- **Database**: H2 (in-memory for development, easily configurable for production)
- **Build Tool**: Maven
- **Java Version**: 8+

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6 or higher (or use included Maven wrapper)

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd task-list-app
   ```

2. Build the application:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

### H2 Database Console

For development purposes, you can access the H2 database console at:
```
http://localhost:8080/h2-console
```

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:taskdb`
- Username: `sa`
- Password: `password`

## API Endpoints

The application provides both web interface and REST API endpoints:

### Web Endpoints
- `GET /` - Main task list page
- `POST /tasks` - Create new task
- `POST /tasks/{id}/toggle` - Toggle task completion
- `POST /tasks/{id}/delete` - Delete task
- `GET /tasks/{id}/edit` - Edit task form
- `POST /tasks/{id}/edit` - Update task
- `GET /search` - Search and filter tasks

### REST API Endpoints
- `GET /api/tasks` - Get all tasks
- `GET /api/tasks/{id}` - Get task by ID
- `POST /api/tasks` - Create new task
- `PUT /api/tasks/{id}` - Update task
- `PATCH /api/tasks/{id}/toggle` - Toggle task completion
- `DELETE /api/tasks/{id}` - Delete task
- `GET /api/tasks/stats` - Get task statistics

## Usage

### Creating Tasks
1. Click the "Add New Task" button or press `Ctrl+N`
2. Fill in the task details:
   - **Title**: Required field for task name
   - **Description**: Optional detailed description
   - **Priority**: Choose from High, Medium, or Low
   - **Due Date**: Optional deadline for the task

### Managing Tasks
- **Complete Task**: Click the circle icon next to the task title
- **Edit Task**: Click the edit (pencil) icon
- **Delete Task**: Click the delete (trash) icon
- **Search**: Use the search box in the sidebar
- **Filter**: Use the status and priority dropdowns

### Keyboard Shortcuts
- `Ctrl+N` - Open new task modal
- `Escape` - Close open modals

## Project Structure

```
src/
├── main/
│   ├── java/com/example/tasklist/
│   │   ├── TaskListApplication.java      # Main Spring Boot application
│   │   ├── Task.java                     # Task entity model
│   │   ├── TaskRepository.java           # Data access layer
│   │   ├── TaskService.java              # Business logic layer
│   │   └── TaskController.java           # Web and REST controllers
│   └── resources/
│       ├── static/
│       │   ├── css/style.css             # Custom CSS styles
│       │   └── js/app.js                 # JavaScript functionality
│       ├── templates/
│       │   ├── index.html                # Main task list page
│       │   └── edit-task.html            # Task editing page
│       └── application.properties        # Application configuration
└── test/                                 # Test files (to be added)
```

## Configuration

Key configuration properties in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:taskdb
spring.datasource.username=sa
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080

# H2 Console
spring.h2.console.enabled=true
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Future Enhancements

- [ ] User authentication and authorization
- [ ] Task categories/tags
- [ ] File attachments for tasks
- [ ] Task sharing and collaboration
- [ ] Email notifications for due dates
- [ ] Export tasks to CSV/PDF
- [ ] Dark mode toggle
- [ ] Task templates
- [ ] Recurring tasks
- [ ] Time tracking

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

If you encounter any issues or have questions, please create an issue in the repository or contact the development team.

---

**Happy Task Managing! 🚀**
