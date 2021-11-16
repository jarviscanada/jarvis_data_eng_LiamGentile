#! /bin/sh

# assigning the CLI arguments to variables 
# the command will be position 1, username position 2, password position 3 
cmd=$1
db_username=$2
db_password=$3

# starting the docker if sudo systemctl status docker fails
sudo systemctl status docker || systemctl start docker

# checking the container status and assigning to a variable
# $? gives us the exit status of the previous command
docker container inspect jrvs-psql
container_status=$?

# case: command is create
case $cmd in 
  create)
  
  # check if container exists already (container exit status = 0)
  if [ $container_status -eq 0 ]; then
	echo 'Container already exists'
	exit 1	
  fi

  # ensure that all 3 arguments are given 
  if [ $# -ne 3 ]; then
  	echo 'Create requires username and password'
    	exit 1
  fi
  
  # creating volume to store data
  docker volume create pgdata

  # running docker with CLI given credentials
  docker run --name jrvs-psql -e POSTGRES_USER=$db_username \
  POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data \
  -p 5432:5432 postgres:9.6-alpine
  exit $?
  ;;

  # case: command is start or stop
  start|stop) 

  # check if container doesn't yet exist (container exit status = 0)
  if [ $container_status -eq 1 ]; then
  	echo 'Container has not been created yet'
	exit 1
  fi

  # otherwise either start or stop the container
  docker container $cmd jrvs-psql
  exit $?
  ;;	
  
  # case: command is something other than stop, start, or create 
  *)
  echo 'Illegal command'
  echo 'Commands: start|stop|create'
  exit 1
  ;;
esac 
