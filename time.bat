set parttime=%TIME::=%
set parttime=%parttime:~0,6%
set partdate=%DATE:-=%
set partdate=%partdate:~0,4%%partdate:~5,2%%partdate:~8,2%
echo %partdate%_%parttime%>time.txt
echo a|xcopy time.txt assets
