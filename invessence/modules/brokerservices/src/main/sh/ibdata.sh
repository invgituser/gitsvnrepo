#!/bin/sh

DATA=/inv/data/fetch
IBUSER=invessen
IBPWD=invest12
IBHOST=ftp3.interactivebrokers.com 

ftp -ni ${IBHOST} <<EOD
quote USER ${IBUSER}
quote PASS ${IBPWD}
lcd ${DATA}
cd Outgoing
mget 2014*
quit
EOD

