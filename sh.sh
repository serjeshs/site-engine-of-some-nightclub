Y=2018
M=06
D=16
export GIT_COMMITTER_DATE="$Y-$M-$D 12:00:00"
export GIT_AUTHOR_DATE="$Y-$M-$D 12:00:00"
git add ./
git commit --date="$Y-$M-$D 12:00:00" -m "EventViewComponent set as common component"
git push origin master

