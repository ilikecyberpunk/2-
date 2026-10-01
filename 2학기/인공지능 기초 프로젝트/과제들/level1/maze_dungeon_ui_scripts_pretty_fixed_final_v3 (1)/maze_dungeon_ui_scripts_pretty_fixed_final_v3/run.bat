@echo off
cd /d "%~dp0"
title Maze Escape - Launcher

echo ======================================
echo          MAZE ESCAPE GAME
echo ======================================
echo.
echo [1/3] Java source compile...
javac Main.java
if errorlevel 1 (
  echo.
  echo 컴파일에 실패했습니다.
  echo JDK가 설치되어 있고 PATH에 등록되어 있는지 확인하세요.
  pause
  exit /b 1
)

echo [2/3] Java game server starting...
start "Maze Server" cmd /k "cd /d \"%~dp0\" && java Main"

echo [3/3] Waiting for server...
timeout /t 2 /nobreak >nul
start "" "http://localhost:8080/"

echo.
echo 브라우저가 열립니다.
echo 주소: http://localhost:8080/
echo.
echo 게임 서버 창은 종료하지 마세요.
exit /b 0
