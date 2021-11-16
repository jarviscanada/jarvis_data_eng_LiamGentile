#adding help function to explain the switch function
Help()
{
	echo "This script creates host_info and host_usage tables if  they don't exist already."
	echo ""
	echo "options:"
	echo "switch     Switch to the host_agent database."
}

#declaring a function/option to switch to the host_agent database
declare -f | less
<snip>
switch ()
	{
		\c host_agent
	}
<snip>


#creating `host_info` table if it does not exist
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
	(
	time_stamp TIMESTAMP NOT NULL, 
	host_id SERIAL REFERENCES host_info (id) NOT NULL, 
	memory_free INT NOT NULL, 
	cpu_idle INT NOT NULL, 
	cpu_kernel INT NOT NULL, 
	disk_io INT NOT NULL, 
	disk_available INT NOT NULL
	);

#creating `host_usage` table if it does not exist
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage 
	(
	time_stamp TIMESTAMP NOT NULL, 
	host_id SERIAL REFERENCES host_info (id) NOT NULL, 
	memory_free INT NOT NULL, cpu_idle INT NOT NULL, 
	cpu_kernel INT NOT NULL, disk_io INT NOT NULL, 
	disk_available INT NOT NULL
	);
