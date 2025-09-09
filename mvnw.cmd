@ECHO OFF
setlocal
set WRAPPER_JAR=.mvn\wrapper\maven-wrapper-3.2.0.jar
set PROPS_FILE=.mvn\wrapper\maven-wrapper.properties
if not exist "%WRAPPER_JAR%" (
  echo Downloading Maven Wrapper...
  powershell -Command "(New-Object Net.WebClient).DownloadFile(((Get-Content %PROPS_FILE%).Split('=')[1]), '%WRAPPER_JAR%')"
)
java -jar "%WRAPPER_JAR%" -Dmaven.multiModuleProjectDirectory=%CD% -Dmaven.wrapper.disableScripts=false %*
