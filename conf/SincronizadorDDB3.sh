. /home/iovanny/NetBeansProjects/base_distribuida/conf/environment
#. /etc/environment
cd $RHOME
java -classpath $CLASSPATH basedistribuida.process.SincronizaMaq3Process >> $LOGS_PATH/cron/SincronizadorDDB.txt  &
