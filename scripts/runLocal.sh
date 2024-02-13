#!/bin/sh

export APP_SERVER_PORT=9091
export APP_GET_VOUCHER_API="http://localhost:9091/mocks/voucher"

exec java -jar gm-cost-delivery.jar

