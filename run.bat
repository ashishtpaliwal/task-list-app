@echo off
echo Starting Task List Application...
echo.

REM Set JAVA_HOME if not already set
if "%JAVA_HOME%"=="" (
    set "JAVA_HOME=C:\Program Files\Java\jdk1.8.0_202"
)

REM Check if Maven is available
where mvn >nul 2>&1
if %errorlevel% equ 0 (
    echo Using Maven to run the application...
    mvn spring-boot:run
) else (
    echo Maven not found. Trying Maven wrapper...
    if exist "mvnw.cmd" (
        mvnw.cmd spring-boot:run
    ) else (
        echo Neither Maven nor Maven wrapper found.
        echo Please install Maven or ensure JAVA_HOME is set correctly.
        echo Current JAVA_HOME: %JAVA_HOME%
        pause
    )
)
