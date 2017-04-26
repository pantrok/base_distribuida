. /home/daniel/environment
#. /etc/environment
cd $RHOME
java -classpath $CLASSPATH basedistribuida.process.SincronizaMaq1Process >> $LOGS_PATH/cron/SincronizadorDDB.txt  &
