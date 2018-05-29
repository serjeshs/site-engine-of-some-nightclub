Y=2018
M=05
D=29
export GIT_COMMITTER_DATE="$Y-$M-$D 12:00:00"
export GIT_AUTHOR_DATE="$Y-$M-$D 12:00:00"
git add ./
git commit --date="$Y-$M-$D 12:00:00" -m "$M $D $Y"
git push origin master

