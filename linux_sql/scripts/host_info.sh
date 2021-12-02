#!/bin/bash

# assigning CLI arguments to variables 
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# ensuring that the user provides five arguments
if [ $# -ne 5 ]; then
      echo 'This script requires five arguments:'
      echo 'psql_host, psql_port, db_name, psql_user, psql_password'
      exit 1
fi

# assigning hardware info commands to variables and pulling out our desired columns
lscpu_out=`lscpu`
mem_info_out=$(cat /proc/meminfo)

hostname=$(hostname -f)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs) 
 
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{$1=$2=""; print $0}' | xargs)

cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{$1=$2=""; print $0}' | xargs)
 
l2_cache=$(echo "$lscpu_out"  | egrep "^L2[[:space:]]cache:" | awk '{print $3+0}' | xargs)

total_mem=$(echo "$mem_info_out"  | egrep "^MemTotal:" | awk '{print $2+0}' | xargs)

timestamp=$(date +"%Y-%m-%d %H:%M:%S")

insert_stmt="INSERT INTO host_info(host_name, cpu_number, cpu_architecture, 
	     cpu_model, cpu_mhz, L2_cache, total_mem, time_stamp) 
	     VALUES('$host_name', '$cpu_number', '$cpu_architecture', 
            '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')"

# exporting password variable for psql child process to inherit
export PGPASSWORD=$psql_password 

# inserting the data into a database using psql, the cli arguments, and the sql insert_stmt
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
