# Fetching Data Automation

A script was made for the purpose of having a constantly updating table of airports and weather data. It updates at regular intervals and fetches data from the https://tgftp.nws.noaa.gov/data/observations/metar/stations/ address.

___
## Installation and executing the script
To run and execute the script at regular intervals, the following steps must be followed.

- Download the script `fetch-data.sh`
- Place the downloaded script in the folder `/etc/cron.hourly` so the script will be executed every hour (how often the METAR data is updated).
- If the script isn't executing properly, run `sudo chmod u+x /etc/cron.hourly/fetch-data.sh`
## Script explanation
The script sends a GET request to the application to receive a list of subscribed airports. The list is mapped to an array and iterated through.

For every ICAO_CODE in the array, a new GET request is sent to fetch new available METAR data.

The data is available in the form of a .txt file, and the second line in the file represents the METAR data that should be parsed. The second line of the file is trimmed and prepared as a JSON payload.

```
trimmed_line=$(echo "$line" | tr -d '\r\n')
json_payload=$(jq -n --arg d "$trimmed_line" '{data: $d}')
```
A POST request is then made to the application, and the METAR data is saved as the newest available data for the airport.

```
 curl -s -X POST "http://localhost:8080/airport/${ICAO_CODE}/METAR" \
                        -H "Content-Type: application/json" \
                        -d "$json_payload"
```
If no data is available for an airport, the script echoes a warning message.