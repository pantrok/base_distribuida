. /home/iovanny/NetBeansProjects/base_distribuida/conf/environment
#. /etc/environment
cd $RHOME
java -classpath $CLASSPATH basedistribuida.process.SincronizaMaq4Process >> $LOGS_PATH/cron/SincronizadorDDB.txt  &

