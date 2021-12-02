# Linux Cluster Monitoring Agent

## Introduction

The goal of this project was to create a set of tools that allow a user to monitor different nodes connected in a Linux cluster by tracking each node's hardware specifications and resource usage in real time and connecting that data automatically to a database.

This project uses GCP to start a Virtual Machine, and from within the VM a docker instance is created and a PostgreSQL instance is provisioned on docker.  

- **Tools & technologies used:** *Linux CentOS 7, VNC, Bash, Git, PostgreSQL, docker, crontab, Github*
- **Project methodology:** *Agile & Scrum* 

## Quick Start

- `./psql_docker.sh start stop create`
- create tables using `ddl.sql`
- insert hardware data into database using `host_info.sh`
- insert usage data into database using `host_usage.sh`
- setup Crontab to automate `host_usage.sh` data insertion, see under `host_usage.sh` below
   
## Architecture & Design

![linux_cluster_architecture drawio](https://user-images.githubusercontent.com/80293145/142264534-01c87432-27c3-47f2-850c-2728ab94bbbd.png)

A bash agent consisting of two scripts (`host_info.sh` and `host usage.sh`) gathers data from each node and then inserts the data into a psql instance. The bash agent is installed on each node. `host_info.sh` is only run once at the time of installation, whereas `host_usage.sh` is triggered by a `crontab` job every minute.  


## Database & Tables

For this project, there is one database, named `host_data`. It consists of two tables, `host_info` and `host_usage`. `host_info` stores hardware specifications data that is assumed to stay constant, and `host_usage` stores resource usage data which is updated every minute by each node in order to track resource usage data over time.  

##### `host_info`
- `id`: a unique identification number and primary key, autoincremented in PGSQL
- `host_name`: a full, unique name for the node 
- `cpu_number`: the number of CPUs
- `cpu_architecture`: a layout of the internals of the CPU (eg. x86_64)
- `cpu_model`: the full model name of the CPU
- `cpu_mhz`: a measurement of the transmission speed of the CPU (in mhz)
- `l2_cache`: level 2 cache memory, separate from the chip core (in KB)
- `total_mem`: total memory usage (in KB)
- `time_stamp`: date and time of data retrieval in UTC

##### `host_usage`
- `host_id`: a unique identification number and foreign key, points to `id` from `host_info`
- `memory_free`: amount of memory free (in MB)
- `cpu_idle`: cpu idle percentage
- `cpu_kernel`: cpu kernel percentage
- `disk_io`: number of disk I/O
- `disk_available`: disk available (in MB)
- `time_stamp`: date and time of data retrieval in UTC

## Scripts

### Shell Scripts

##### `host_info.sh`

This script collects hardware data and inserts the data into a psql instance. It is assumed that this data is static, and so the script will be executed only once. 

The script consists of 5 essential parts:
1. Assigning CLI arguments to variables
2. Ensuring the user provides 5 arguments (using `if [ ]; then`)
3. Assigning hardware info commands to variables and extracting desired columns to variables
4. An SQL insert statement to get the data into the database
5. A `psql` command to execute the insert statement with the CLI arguments

To execute the script you would run `./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password`. 

----------------------

##### `host_usage.sh`

This script collects resource usage data and inserts the data into a psql instance. This script is executed every minute using `crontab` to track changes in the data over time. 

The script consists of 5 essential parts:
1. Assigning CLI arguments to variables
2. Ensuring the user provides 5 arguments (using `if [ ]; then`)
3. Assigning resource usage data commands to variables and extracting desired columns to variables
4. A SQL insert statement (this time with a subquery insert statement that allows us to pull `host_id` from `id` (`host_info`)
5. A `psql` command to execute the insert statements with the CLI arguments

To execute the script you would run `./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password`. 

**using `crontab` to automate `host_usage.sh` so it collects data every minute**

First we edit the `crontab` with:
- `crontab -e`

Then we write the following in the `crontab`:
-  `* * * * * bash /home/centos/dev/jarvis_data_eng_liam/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password >     /tmp/host_usage.log`

5 stars means the data will be collected every minute (in `crontab` syntax). We pass the output of our script into a `.log` file to save the updates. 

----------------------

##### `psql_docker.sh`

The purpose of this script is to allow a user to `create`, `stop`, or `start` a `psql` container. 

The script is composed of the following elements:
1. Capturing CLI arguments (`cmd`, `db_username`, and `db_password`)
2. Starting the docker and assigning the docker container status to a variable 
3. `Case $cmd in` for the `create` command 
- if the container does not exist already and the user has provided 3 arguments then create a volume and a container with the given credentials
4. `Case $cmd in` for the `start` and `stop` commands
- if the container exists already then we either `start` or `stop` the docker container
5. `Case $cmd in` for anything other than `create`/`start`/`stop`
- print illegal command and display valid commands


The script is used like this `./scripts/psql_docker.sh start|stop|create db_username db_password`


### SQL Scripts

##### `ddl.sql`

This is a simple script that creates two tables in the `host_agent` database if they don't exist already. These tables are `host_info` and `host_usage`. The script uses standard Postgres SQL syntax. 

To execute the script you would run `psql -h localhost postgres -d host_agent -f sql/ddl.sql`.

----------------------

##### `queries.sql`

This is a script that runs three queries and makes use of a created function. 

**Query 1**
- This query groups hosts by `cpu_number` and sorts by `total_mem` descending.
- To order by `total_mem` within each `cpu_number` group, I had to use a window function.

**round5 function**
- This is a function that turns the `time_stamp` column into five minute intervals. 
- I implemented this to make the second and third queries more succinct.

**Query 2**
- This query returns average used memory (in %) per host and five minute interval. 
- It required creating a new column (`avg_used_mem_percentage`) from the `total_mem` and `memory_free` columns.
- It also entailed a join statement because the query requires data from both tables. 

**Query 3**
- This query detects if the `crontab` job is failing.
- It accomplishes this by returning groups of `host` and five minute intervals where the number of rows (data) inserted is less than 3. 
- We can assume that these represent failures because our `crontab` job is running once per minute. 

## Test

Testing was done manually. I ran the scripts in the CLI, and checked the commands against the postgres database to ensure that the commands executed correctly. SQL queries were tested using a mix of sample data and real single node data from my own virtual machine. 

## Deployment

- Deployed project to Github remote repository.
- Docker container and postgres instance provisioned for data management.

## Improvements

1. Adding help functions to the `.sh` scripts. 
2. Creating more sample data at the time of creating the database to better test SQL queries. 
3. Spending more time debugging code before pushing to Github (instead of updating many times). 
