#!/bin/bash

#saving hostname to a variable
hostname=$(hostname -f)

#saving cpu information to a variable
lscpu_out=`lscpu`

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs) 

#$1-$2="" is telling awk to ignore fields one and two and print $0 says to print the whole line (in this case without one and two)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{$1=$2=""; print $0}' | xargs)

cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{$1=$2=""; print $0}' | xargs)

#adding +0 to the awk print statement allows me to ignore the K after the number (forced to interpret the 3rd field as a number)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2[[:space:]]cache:" | awk '{print $3+0}' | xargs)

timestamp=$(date +"%Y-%m-%d %H:%M:%S")
