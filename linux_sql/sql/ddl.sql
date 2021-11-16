CREATE TABLE IF NOT EXISTS PUBLIC.host_info 
	(
	id SERIAL PRIMARY KEY NOT NULL, 
	host_name VARCHAR UNIQUE NOT NULL, 
	cpu_number INT NOT NULL, 
	cpu_architecture VARCHAR NOT NULL, 
	cpu_model VARCHAR NOT NULL, 
	cpu_mhz FLOAT NOT NULL, 
	l2_cache INT NOT NULL, 
	total_mem INT NOT NULL, 
	time_stamp TIMESTAMP NOT NULL
	);

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
