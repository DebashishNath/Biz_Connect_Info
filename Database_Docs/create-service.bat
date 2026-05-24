@echo off

REM =========================================
REM School Info Service Generator
REM =========================================

set /p MODULE_NAME=Enter Module Name: 

REM =========================================
REM Base Project Path
REM =========================================

set BASE_PATH=D:\ImpProj\Biz_Connect_Info\Biz_Connect_Source_Codes\src\main\java\biz_connect_info\service

REM =========================================
REM Create Folder
REM =========================================

mkdir "%BASE_PATH%\%MODULE_NAME%"

REM =========================================
REM Create Service Files
REM =========================================

type nul > "%BASE_PATH%\%MODULE_NAME%\%MODULE_NAME%Service.java"

type nul > "%BASE_PATH%\%MODULE_NAME%\%MODULE_NAME%ServiceImpl.java"

type nul > "%BASE_PATH%\%MODULE_NAME%\%MODULE_NAME%ServiceDAL.java"

REM =========================================
REM Success Message
REM =========================================

echo.
echo =========================================
echo Service Layer Created Successfully
echo =========================================
echo Folder:
echo %BASE_PATH%\%MODULE_NAME%
echo.
echo Files Created:
echo 1. %MODULE_NAME%Service.java
echo 2. %MODULE_NAME%ServiceImpl.java
echo 3. %MODULE_NAME%ServiceDAL.java
echo =========================================

pause