#!/bin/bash

AIRPORT_LIST_URL="http://localhost:8080/subscriptions"
METAR_DATA_FETCH_START="https://tgftp.nws.noaa.gov/data/observations/metar/stations/"

mapfile -t ICAO_CODES < <(curl -s "$AIRPORT_LIST_URL" | jq -r ".[].icaoCode")

for ICAO_CODE in "${ICAO_CODES[@]}"; do
    METAR_DATA=$(curl -s "${METAR_DATA_FETCH_START}${ICAO_CODE}.TXT")

    if [ -n "$METAR_DATA" ]; then
        i=0
        while read -r line; do
            i=$((i + 1))
            if [ "$i" -eq 2 ]; then
                trimmed_line=$(echo "$line" | tr -d '\r\n')
                json_payload=$(jq -n --arg d "$trimmed_line" '{data: $d}')

                curl -s -X POST "http://localhost:8080/airport/${ICAO_CODE}/METAR" \
                        -H "Content-Type: application/json" \
                        -d "$json_payload"

            fi
        done <<< "$METAR_DATA"
    else
        echo "No METAR data found for $ICAO_CODE"
    fi
done
