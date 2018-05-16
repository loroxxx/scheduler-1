@echo off cmd

echo -------------------------------------------------------------------------
echo  Build  Script for Windows
echo -------------------------------------------------------------------------

setlocal
set CURRENT_DIR=%~dp0%

call ant

pause
