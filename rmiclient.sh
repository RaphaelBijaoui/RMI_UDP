#!/bin/bash
export SECPOLICY="file:./policy"
java -cp .:rmi -Djava.security.policy=$SECPOLICY rmi.RMIClient $*
