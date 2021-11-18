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

# assigning resource usage data commands to variables and pulling out our desired columns
vmstat_s_out=$(vmstat -s --unit M)
vmstat_t_out=$(vmstat -t)
vmstat_d_out=$(vmstat -d)
df_out=$(df -BM /)

memory_free=$(echo "$vmstat_s_out"  | egrep "free memory$" | awk '{print $1}' | xargs)

cpu_idle=$(echo $vmstat_t_out | awk '{print $40}' | xargs)

cpu_kernel=$(echo $vmstat_t_out | awk '{print $39}' | xargs)

disk_io=$(echo $vmstat_d_out | awk '{print $24}' | xargs)

disk_available=$(echo $df_out | awk '{print $11+0}' | xargs)

timestamp=$(date +"%Y-%m-%d %H:%M:%S")

# getting hostname so that we can fill in host_id 
# from id (host_info column) according to matching host_name 
hostname=$(hostname -f)
host_id="(SELECT id FROM host_info WHERE host_name='$hostname')"

insert_stmt="INSERT INTO host_usage(host_id, memory_free, cpu_idle, 
             cpu_kernel, disk_io,disk_available, time_stamp) 
             VALUES('$host_id', '$memory_free', '$cpu_idle', 
             '$cpu_kernel', '$disk_io', '$disk_available', '$timestamp')"

# exporting password variable for psql child process to inherit
export PGPASSWORD=$psql_password 

# inserting the data into a database using psql, the cli arguments, and the sql insert_stmt
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit 0
