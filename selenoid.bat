powershell -Command "(New-Object Net.WebClient).DownloadFile('https://github.com/aerokube/cm/releases/download/1.7.2/cm_windows_386.exe', 'cm_windows_386.exe')"
docker pull selenoid/vnc_chrome:86.0
cm_windows_386.exe selenoid start --vnc
cm_windows_386.exe selenoid-ui start
mvn test -Dcucumber.filter.tags="@smoke"