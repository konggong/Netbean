. /u/4gl/client/bin/.ba_setup
. /u/4gl/bin/.cfg
. /u/4gl/bin/.addprofile

#PATH=.:/usr/bin:/etc:/usr/informix7/bin:/usr/local/bin:/u/4gl/client/bin:/u/4gl/run
#PATH=/usr/bin:/etc:/usr/sbin:/usr/ucb:$HOME/bin:/usr/bin/X11:/sbin:.
#export PATH

#pww-add
export PATH=/home/pww/4gl/run/:$PATH

if [ -s "$MAIL" ]           # This is at Shell startup.  In normal
then echo "$MAILMSG"        # operation, the Shell checks
fi                          # periodically.

############
umask 002

############
export INFORMIXSERVER=online1
export ONCONFIG=onconfig.online1
#export INFORMIXSERVER=sbaonline8
#export ONCONFIG=onconfig.sbaonline8
export INFORMIXTERM
export TERMCAP=/usr/informix7/etc/termcap.ok
export DBRUNPATH='/u/4gl/client/run'
export KN_LOG_PATH='/u/sba/log/'
export DBOUTPATH=/u/sba/run/out
export DBNAME=ba
#export DBNAME=dbref
export DBTEMP=/tmp

############

alias ba="dbaccess ba"
alias src="cd /u/4gl/client/src"
alias cs="cd /u/4gl/cs_src"
alias log="tail -f /u/sba/log/DATABASE_BA.LOG"
alias run="cd /u/4gl/run"
alias out="cd /u/sba/run/out01/pww"
alias st="cd /u/4gl/cs_src/STORE_PROCEDURE"
alias post="cd /home/pww/4gl/post"
alias rep="cd /home/pww/4gl/report"
alias pww="dbaccess pww"


export PS1="["`hostname`":"'$PWD'"]"

banner = ' PWW '
10.22.13.63 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC/Phz/gzWdjXcoQl1/3m4aQkXswVx6SF5ZEtyumoYC7mPlecCnuhB7rR99GTJIdxN4sxBuk7vewCAymTrrYXfdxyRWIzhda1VPFxJMc8qq7OEEn8iOnkaIjhhKvLJZ5RVtsevl8wvijIh2AQek1N1jeTmDClLf9W27h1syR/JFbvsLw0dKIt9jq1c46W2Ypbm1gCO2eJ32L6XMbKHKGVQZbMK62SHSSifdokx5WkDLmD0+Aw2DcqaOeL3Zz7mOe624F7MQl6tV0/iQeQie4rpja7JOCxotlYzyGhtQatxT8ksO3RvLEN8sF56LCe0idX/pzBa+dP0POzB4ScyJEfff
