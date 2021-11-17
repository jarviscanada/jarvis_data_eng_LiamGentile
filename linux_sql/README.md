# Linux Cluster Monitoring Agent

## Introduction

The goal of this project was to create a set of tools that allow a user to monitor different nodes connected in a Linux cluster by tracking each node's hardware specifications and resource usage in real time and connecting that data automatically to a database.

- Tools & technologies used: *Linux CentOS 7, VNC, Bash, Git, PGSQL, Docker, Crontab*
- Project methodology: *Agile & Scrum* 
   
## Architecture & Design

![linux_cluster_architecture drawio](https://user-images.githubusercontent.com/80293145/142264534-01c87432-27c3-47f2-850c-2728ab94bbbd.png)

A bash agent consisting of two scripts (`host_info.sh` and `host usage.sh`) gathers data from each node and then inserts the data into a psql instance. The bash agent is installed on each node. `host_info.sh` is only run once at the time of installation, whereas `host_usage.sh` is triggered by a `crontab` job every minute.  


## Database & Tables

For this project, there is one database, named `host_data`. It consists of two tables, `host_info` and `host_usage`. `host_info` stores hardware specifications data that is assumed to stay constant, and `host_usage` stores resource usage data which is updated every minute by each node in order to track resource usage data over time.  

###### `host_info`
- `id`: a unique identification number and primary key, autoincremented in PGSQL
- `host_name`: a full, unique name for the node 
- `cpu_number`: the number of CPUs
- `cpu_architecture`: a layout of the internals of the CPU (eg. x86_64)
- `cpu_model`: the full model name of the CPU
- `cpu_mhz`: a measurement of the transmission speed of the CPU (in mhz)
- `l2_cache`: level 2 cache memory, separate from the chip core (in KB)
- `total_mem`: total memory usage (in KB)
- `time_stamp`: date and time of data retrieval in UTC

###### `host_usage`
- `host_id`: a unique identification number and foreign key, points to `id` from `host_info`
- `memory_free`: amount of memory free (in MB)
- `cpu_idle`: cpu idle percentage
- `cpu_kernel`: cpu kernel percentage
- `disk_io`: number of disk I/O
- `disk_available`: disk available (in MB)
- `time_stamp`: date and time of data retrieval in UTC

## Scripts

### Shell Scripts

###### `host_info.sh`
-
-
-

###### `host_usage.sh`
-
-
-

###### `psql_docker.sh`
-
-
-

### SQL Scripts

###### `ddl.sql`

This is a simple script that creates two tables in the `host_agent` database if they don't exist already. These tables are `host_info` and `host_usage`.  

###### `queries.sql`
-
-
-

## Improvements
