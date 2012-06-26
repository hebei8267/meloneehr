@echo off
rem Batch file to run ASTAH on Windows

rem Remove "rem" from following two lines, if you'd like to use j2sdk.
rem set JAVA_HOME=C:\jdk1.6.0_16
rem set PATH=%JAVA_HOME%\bin;%PATH%

set ASTAH_JAR=astah-community.jar
set INITIAL_HEAP_SIZE=16m
set MAXIMUM_HEAP_SIZE=384m

set USER_LANGUAGE=
rem set USER_LANGUAGE=ja
rem set USER_LANGUAGE=en

set USER_COUNTRY=
rem set USER_COUNTRY=JP
rem set USER_COUNTRY=US


rem ASTAH_HOME should be this folder
if not "%ASTAH_HOME%"=="" goto hasAstahHome

if "%OS%"=="Windows_NT" goto setAstahHomeNT

rem *** Windows98 user have to edit here ****
rem set ASTAH_HOME=c:\Program Files\ASTAH-Community

if "%ASTAH_HOME%"=="" goto noAstahHome
if not exist "%ASTAH_HOME%\%ASTAH_JAR%" goto badAstahHome
goto hasAstahHome

rem for Windows2000, XP, NT
:setAstahHomeNT
set ASTAH_HOME=%~dp0

:hasAstahHome

rem set CLASSPATH=%ASTAH_HOME%\%ASTAH_JAR%


set JAVA_OPTS=-Xms%INITIAL_HEAP_SIZE% -Xmx%MAXIMUM_HEAP_SIZE%
if not "%USER_LANGUAGE%"=="" set JAVA_OPTS=%JAVA_OPTS% -Duser.language=%USER_LANGUAGE%
if not "%USER_COUNTRY%"=="" set JAVA_OPTS=%JAVA_OPTS% -Duser.country=%USER_COUNTRY%

if exist "%ASTAH_HOME%\jre\bin\javaw.exe" set PATH=%ASTAH_HOME%\jre\bin;%PATH%

rem run Astah
start javaw %JAVA_OPTS% -jar "%ASTAH_HOME%\%ASTAH_JAR%"  %1 %2 %3
IF ERRORLEVEL 2 goto noJavaw
goto end

:noAstahHome
echo.
echo ASTAH_HOME is not set.  Please set ASTAH_HOME environment variable
echo or edit astah.bat to set ASTAH_HOME.
echo. 
pause
goto end

:badAstahHome
echo.
echo ASTAH_HOME is invalid.  Please check your ASTAH_HOME.
echo. 
pause
goto end

:noJavaw
echo.
echo Failed to run java.
echo Java runtime environment is required to run ASTAH.
echo Please read README.txt in %ASTAH_HOME%
echo and setup Java environment at first.
echo.
echo ASTAH tries to run javaw. It should be in PATH system environment variable.
echo.
echo If you would like to run java in your specified folder, you can edit astah.bat
echo in %ASTAH_HOME%
echo like followings and set your JAVA_HOME.
echo     before:
echo       rem set JAVA_HOME=C:\jdk1.6.0_16
echo       rem set PATH=%%JAVA_HOME%%\bin;%%PATH%%
echo     after:
echo       set JAVA_HOME=C:\jdk1.6.0_16
echo       set PATH=%%JAVA_HOME%%\bin;%%PATH%%
echo.
echo.
pause
goto end

:end
