@echo off

set APP_SERVER_PORT=9091
set APP_GET_VOUCHER_API=http://localhost:9091/mocks/voucher

call java -jar gm-cost-delivery.jar
