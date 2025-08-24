# Deployment Guide for Task List App

Your Task List application is now ready for deployment! Here are several deployment options:

## 🚀 Quick Deployment Options

### Option 1: Heroku (Recommended for Spring Boot)

1. **Install Heroku CLI** from https://devcenter.heroku.com/articles/heroku-cli

2. **Login and create app:**
   ```bash
   heroku login
   heroku create your-task-list-app
   ```

3. **Deploy:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git push heroku main
   ```

4. **Open your app:**
   ```bash
   heroku open
   ```

### Option 2: Railway

1. **Visit** https://railway.app
2. **Connect your GitHub repository**
3. **Select your task-list-app repository**
4. **Railway will auto-detect Spring Boot and deploy**

### Option 3: Render

1. **Visit** https://render.com
2. **Create new Web Service**
3. **Connect your GitHub repository**
4. **Use these settings:**
   - Build Command: `mvn clean package -DskipTests`
   - Start Command: `java -jar target/task-list-app-0.0.1-SNAPSHOT.jar`
   - Environment: Java 17

### Option 4: Docker Deployment

1. **Build Docker image:**
   ```bash
   docker build -t task-list-app .
   ```

2. **Run locally:**
   ```bash
   docker run -p 8080:8080 task-list-app
   ```

3. **Deploy to any Docker hosting service** (DigitalOcean, AWS, GCP, etc.)

## 📋 Pre-Deployment Checklist

✅ **Files Created:**
- `.gitignore` - Excludes unnecessary files
- `netlify.toml` - Build configuration
- `application-production.properties` - Production settings
- `Dockerfile` - Container configuration
- `system.properties` - Java version specification

✅ **Production Features:**
- H2 console disabled in production
- Thymeleaf caching enabled
- Security headers configured
- Environment variable support
- Logging optimized

## 🔧 Environment Variables (Optional)

Set these in your hosting platform:

```
SPRING_PROFILES_ACTIVE=production
DATABASE_URL=jdbc:h2:mem:taskdb
DB_USERNAME=sa
DB_PASSWORD=password
PORT=8080
```

## 🌐 Local Testing

Before deploying, test the production build locally:

```bash
# Build the application
mvn clean package -DskipTests

# Run with production profile
java -Dspring.profiles.active=production -jar target/task-list-app-0.0.1-SNAPSHOT.jar
```

## 📱 Post-Deployment

After successful deployment:

1. **Test all features:**
   - Create tasks
   - Edit tasks
   - Toggle completion
   - Search functionality
   - Filter by priority/status

2. **Monitor logs** for any issues

3. **Share your app URL** with others!

## 🔄 Future Updates

To update your deployed app:

1. **Make changes locally**
2. **Commit to Git:**
   ```bash
   git add .
   git commit -m "Update description"
   git push
   ```
3. **Most platforms auto-deploy** from Git pushes

## 🆘 Troubleshooting

**Common Issues:**
- **Port binding**: Ensure your app uses `PORT` environment variable
- **Memory limits**: Spring Boot apps need at least 512MB RAM
- **Build timeouts**: Increase build timeout in hosting platform settings

**Need Help?**
- Check application logs in your hosting platform
- Verify environment variables are set correctly
- Ensure Java 17 is specified in platform settings

---

**Your Task List App is production-ready! 🎉**

Choose your preferred deployment method above and get your app online in minutes!
