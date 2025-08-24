# 🚀 Git Deployment Guide for Task List App

Since Git is not currently installed on your system, here's a complete guide to deploy your Task List App to GitHub.

## 📋 Prerequisites

1. **Install Git**: Download from https://git-scm.com/downloads
2. **Create GitHub Account**: Sign up at https://github.com if you don't have one
3. **Install Java 8+**: Ensure Java is properly installed and JAVA_HOME is set

## 🛠️ Step-by-Step Deployment

### Step 1: Install Git
```bash
# Download and install Git from https://git-scm.com/downloads
# Verify installation:
git --version
```

### Step 2: Configure Git (First Time Only)
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### Step 3: Initialize Repository
```bash
# Navigate to your project directory
cd C:\Users\ashis\CascadeProjects\task-list-app

# Initialize Git repository
git init

# Add all files to staging
git add .

# Create initial commit
git commit -m "Initial commit: Task List App with Spring Boot"
```

### Step 4: Create GitHub Repository
1. Go to https://github.com
2. Click "New repository" (+ icon)
3. Repository name: `task-list-app`
4. Description: `A modern task management application built with Spring Boot`
5. Make it **Public** (recommended for portfolio)
6. **Don't** initialize with README (we already have one)
7. Click "Create repository"

### Step 5: Connect Local to GitHub
```bash
# Add remote repository (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/task-list-app.git

# Push to GitHub
git branch -M main
git push -u origin main
```

## 🔄 Future Updates

After making changes to your code:
```bash
git add .
git commit -m "Description of your changes"
git push origin main
```

## 🌐 Deployment Options from GitHub

Once your code is on GitHub, you can deploy to:

### Option 1: Heroku
```bash
# Install Heroku CLI, then:
heroku create your-app-name
git push heroku main
```

### Option 2: Railway
1. Visit https://railway.app
2. Connect GitHub repository
3. Auto-deploys on every push

### Option 3: Render
1. Visit https://render.com
2. Connect GitHub repository
3. Set build command: `mvn clean package -DskipTests`
4. Set start command: `java -jar target/task-list-app-0.0.1-SNAPSHOT.jar`

## 📁 Files Ready for Git

✅ **Project Structure**:
```
task-list-app/
├── src/main/java/com/example/tasklist/
│   ├── TaskListApplication.java
│   ├── Task.java
│   ├── TaskRepository.java
│   ├── TaskService.java
│   └── TaskController.java
├── src/main/resources/
│   ├── templates/
│   │   ├── index.html
│   │   └── edit-task.html
│   ├── static/
│   │   ├── css/style.css
│   │   └── js/app.js
│   ├── application.properties
│   └── application-production.properties
├── .gitignore
├── pom.xml
├── README.md
├── LICENSE
├── CONTRIBUTING.md
├── Dockerfile
├── netlify.toml
└── system.properties
```

## 🎯 Quick Commands Summary

```bash
# One-time setup
git init
git add .
git commit -m "Initial commit: Task List App"
git remote add origin https://github.com/YOUR_USERNAME/task-list-app.git
git push -u origin main

# Regular updates
git add .
git commit -m "Your update message"
git push origin main
```

## 🆘 Troubleshooting

**Common Issues:**
- **Git not recognized**: Install Git and restart terminal
- **Permission denied**: Check GitHub credentials
- **Large files**: Ensure target/ folder is in .gitignore
- **Merge conflicts**: Use `git pull` before pushing

## 📱 After Deployment

Your repository will be available at:
`https://github.com/YOUR_USERNAME/task-list-app`

Share this link to showcase your project! 🎉

---

**Ready to deploy? Install Git and follow the steps above!**
