#!/bin/bash
CURRENT_TIME=$(date +%s);
LOG_FILE="/var/log/site-engine/ci-"CURRENT_TIME".log";
COMPANY_PACKAGE="by.ladyka";
APPLICATION_NAME="club";
APPLICATION_PACKAGE=${COMPANY_PACKAGE}"."${APPLICATION_NAME};

cd /home/appuser/${APPLICATION_NAME};
COMMIT=$(git log --pretty=format:'%h' -n 1);
git pull;
COMMIT_NEW=$(git log --pretty=format:'%h' -n 1);
PID=$(ps -ef | grep "build/libs/${APPLICATION_NAME}"  | grep -v grep | awk '{ print $2 }')

function buildAndStartApplication {
    rm -rf build;

#    rm -rf ../resources/static;
#    mkdir ../resources/static;
#    ng build -op ../resources/static -prod --aot=false;

    cd src/main/angular;
    rm -rf /home/appuser/frontbuld;
    echo "ls /home/appuser/frontbuld/" >> $LOG_FILE;
    ls /home/appuser/frontbuld/  >> $LOG_FILE;
    mkdir /home/appuser/frontbuld;
    ng build --output-path /home/appuser/frontbuld --aot=false;
    echo "ls /home/appuser/frontbuld/"  >> $LOG_FILE;
    ls /home/appuser/frontbuld/  >> $LOG_FILE;
    cd ../../../ ;

    ./gradlew build;
    ls build/libs;
    java -jar  build/libs/*.jar;
}

if [ "$COMMIT" == "$COMMIT_NEW" ]; then
   echo "Have not updates! :("
       if [ -z "$PID" ]
       then
       		echo "Application is not running, start!";
       		buildAndStartApplication;
       else
       		echo "Application already ruining!"
       fi
else
    if [ -z "$PID" ]
    then
        echo "Application is already stopped"
    else
        kill $PID
        echo "Application killed"
    fi

    #backup
    #FILE_NAME="${APPLICATION_NAME}-"$(date +%Y)-$(date +%m)-$(date +%d)_$(date +%H)-$(date +%M)-$(date +%S)".sql";
    #mysqldump -u andreiuser -pQX3M6IbCMCL andrei >> /backup/${APPLICATION_NAME}/$FILE_NAME

	#run
	echo "Application start";
    buildAndStartApplication;
fi

#sendState
