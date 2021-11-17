# Linux Cluster Monitoring Agent

## Introduction

The goal of this project was to create a set of tools that allow a user to monitor different nodes connected in a Linux cluster by tracking each node's hardware specifications and resource usage in real time and connecting that data automatically to a database.
   
## Architecture & Design

## Database & Tables

For this project, there is one database, named `host_data`. It consists of two tables, `host_info` and `host_usage`. `host_info` stores hardware specifications data that is assumed to stay constant, and `host_usage` stores resource usage data which is updated every minute by each node in order to track resource usage data over time.  

`host_info`
- `id`: a unique identification number and primary key, autoincremented in PGSQL
- `host_name`: a full, unique name for the node 
- `cpu_number`: the number of CPUs
- `cpu_architecture`: a layout of the internals of the CPU (eg. x86_64)
- `cpu_model`: the full model name of the CPU
- `cpu_mhz`: a measurement of the transmission speed of the CPU (in mhz)
- `l2_cache`: level 2 cache memory, separate from the chip core (in KB)
- `total_mem`: total memory usage (in KB)
- `time_stamp`: date and time of data retrieval in UTC

`host_usage`
- `host_id`: a unique identification number and foreign key, points to `id` from `host_info`
- `memory_free`: a full, unique name for the node 
- `cpu_idle`: the number of CPUs
- `cpu_kernel`: a layout of the internals of the CPU (eg. x86_64)
- `disk_io`: the full model name of the CPU
- `disk_available`: a measurement of the transmission speed of the CPU (in mhz)
- `time_stamp`: level 2 cache memory, separate from the chip core (in KB)

## Scripts

## Improvements
