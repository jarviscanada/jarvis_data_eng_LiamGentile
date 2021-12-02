/*Query 1
This query returns hosts grouped by cpu_number
and ordered by total memory descending. 
The window function allows us to order total_mem
within each cpu_number group. 
*/

select 
  cpu_number, 
  id, 
  total_mem 
from 
  host_info 
group by 
  cpu_number, 
  id 
order by 
  cpu_number, 
  sum(total_mem) over (partition by cpu_number), 
  total_mem DESC;

/*round5 function
This function allows us to return timestamps in five 
minute blocks.It is used in the second and third queries. 
*/

CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS 
$$ 
BEGIN 
    RETURN date_trunc('hour', ts) + date_part('minute', 
ts):: int / 5 * interval '5 min';
END;
$$ 
    LANGUAGE PLPGSQL;

/*Query 2
This query returns average used memory (as a %)
for each host and 5 minute interval. It requires 
creating a new column from the total_mem and 
memory_free columns in order to calculate average
used memory, as well as a join because we need 
information from both tables.  
*/

select 
  host_id, 
  host_name, 
  round5(host_usage.time_stamp) as five_minute_interval, 
  AVG(
    (
      (total_mem - memory_free) / total_mem
    ) * 100
  ) as avg_used_mem_percentage 
from 
  host_usage 
  inner join host_info on host_info.id = host_usage.host_id 
group by 
  host_usage.time_stamp, 
  host_id, 
  host_name;

/*Query 3
This query allows us to see if there are any errors with 
our crontab job. This query returns only five minute
intervals and host_ids where the number of data points 
was less than 3 (implying an error as our crontab is 
inserting data every minute).
*/

select 
  host_id, 
  round5(time_stamp) as five_minute_interval, 
  count(*) as num_data_points 
from 
  host_usage 
group by 
  five_minute_interval, 
  host_id 
having 
  count(*) < 3 
order by 
  round5(time_stamp);

