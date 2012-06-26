@echo off
rem Batch file to compile a sample of Astah API on Windows

rem Remove "rem" from following two lines, if you'd like to use j2sdk.
rem set JAVA_HOME=C:\jdk1.6.0_16
rem set PATH=%JAVA_HOME%\bin

rem set ASTAH_JAR=..\..\..\..\astah-pro.jar
rem set ASTAH_JAR=..\..\..\..\\astah-uml.jar
rem set ASTAH_JAR=..\..\..\..\astah-community.jar
set API_JAR=..\..\..\..\astah-api.jar
set CLASSPATH=%ASTAH_JAR%;%API_JAR%

rem compile
javac -classpath %CLASSPATH% *.java
IF ERRORLEVEL 2 goto noJavac
goto end

:noJavac
echo.
echo Failed to compile.
echo Java SDK is required to compile.
echo.
pause
goto end

:end