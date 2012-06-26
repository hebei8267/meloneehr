@echo off
rem Batch file to run a sample of Astah API on Windows

rem Remove "rem" from following two lines, if you'd like to use j2sdk.
rem set JAVA_HOME=C:\jdk1.6.0_16
rem set PATH=%JAVA_HOME%\bin;%PATH%

set ASTAH_COM_JAR=..\..\..\..\astah-community.jar
set ASTAH_UML_JAR=..\..\..\..\astah-uml.jar
set ASTAH_PRO_JAR=..\..\..\..\astah-pro.jar
set API_JAR=..\..\..\..\astah-api.jar
set CLASSPATH=%ASTAH_COM_JAR%;%ASTAH_UML_JAR%;%ASTAH_PRO_JAR%;%API_JAR%;

set INITIAL_HEAP_SIZE=16m
set MAXIMUM_HEAP_SIZE=256m

set JAVA_OPTS=-Xms%INITIAL_HEAP_SIZE% -Xmx%MAXIMUM_HEAP_SIZE%
set JAVA_OPTS=%JAVA_OPTS% -classpath %CLASSPATH%

rem run
java %JAVA_OPTS% APIForReadingModelsSample %1 %2
IF ERRORLEVEL 2 goto noJavaw
pause
goto end

:noJavaw
echo.
echo Failed to run java.
echo Java runtime environment is required to run Astah.
echo Please read README.txt in %ASTAH_HOME%
echo and setup Java environment at first.
echo.
echo ASTAH tries to run javaw. It should be in PATH system environment variable.
echo.
echo If you would like to run java in your specified folder, you can edit astah.bat(astah-std.bat,astah-pro.bat)
echo in %ASTAH_HOME%
echo like followings and set your JAVA_HOME.
echo     before:
echo       rem set JAVA_HOME=C:\jdk1.6.0_16
echo       rem set PATH=%JAVA_HOME%\bin;%PATH%
echo     after:
echo       set JAVA_HOME=C:\jdk1.6.0_16
echo       set PATH=%JAVA_HOME%\bin;%PATH%
echo.
echo.
pause
goto end

:end
