#/bin/sh

DATE=`date +%Y%m%d`
BASEDIR=/opt/tomcat/webapps
FILENAME=toop-demo-ui-dc-0.10.5-SNAPSHOT.war

if [ -f ~/$FILENAME ]
  then
    echo stopping Tomcat
    sudo service tomcat stop

    echo Cleaning exisiting dirs
    cd $BASEDIR
    sudo mv ROOT/ ~/dc.$DATE
    sudo chmod 777 ~/dc.$DATE

    echo ROOT
    APPDIR=$BASEDIR/ROOT
    [ ! -d $APPDIR ] && sudo mkdir $APPDIR
    sudo cp ~/$FILENAME $APPDIR
    cd $APPDIR
    sudo unzip -q $FILENAME && sudo rm $FILENAME
    sudo rm $APPDIR/WEB-INF/classes/private-*.properties
    sudo rm $APPDIR/WEB-INF/classes/dcui.properties
    sudo mv $APPDIR/WEB-INF/classes/dcui.freedonia-acc.properties $APPDIR/WEB-INF/classes/dcui.properties
    sudo rm $APPDIR/WEB-INF/classes/toop-interface.properties
    sudo mv $APPDIR/WEB-INF/classes/toop-interface.freedonia-acc.properties $APPDIR/WEB-INF/classes/toop-interface.properties

    echo Done!
  else
    echo "Source file ~/$FILENAME not existing!"
fi
