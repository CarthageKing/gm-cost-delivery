# gm-cost-delivery

## Introduction

This is an example application coded to meet the requirements for one of GM company's technical challenges.

## Prerequisites

This application was developed and run with **Java 17**, however the compiler source and target is set to Java 8, so this project should be possible to be built, tested and run on Java 8. Before doing anything else, ensure you have the recommended version of Java JDK installed. It requires that version of Java since the application is built using Spring Boot 2.7.x, which at the minimum requires Java 8 to build and run. For more information, please refer to [here](https://endoflife.date/spring-boot#java-compatibility).

## Building The Application

This application has been tested on Linux and Windows. To build the application after having cloned the repository from GitHub, execute the following command in the base project folder:

Linux:
```shell
./mvnw clean package -e -DskipITs
```

Windows:
```shell
.\mvnw.cmd clean package -e -DskipITs
```

This will download all necessary dependencies to build the application, run tests against it, and then finally produce a zip file inside the `target` folder. The file is usually named `gm-cost-delivery-<version>-zip-dist.zip` e.g. `gm-cost-delivery-0.0.1-SNAPSHOT-zip-dist.zip`.

## Running the Application

Extract the contents of that `zip-dist` file. Inside, there should be a couple of script files (`.bat` and `.sh`) as well as a `gm-cost-delivery.jar` file. Use the appropriate script file to execute the application depending on your environment.

Linux:
```shell
./runLocal.sh
```

Windows:
```shell
.\runLocal.bat
```

This will run the application on your local workstation. You can use this for testing the application. The default server port used is 9091 and can be changed by editing the script files.

Assuming nothing is changed in the script files, the app can be checked if it is running by executing the example request:

```sh
curl --location 'http://localhost:9091/cost_delivery/_calculate' \
--header 'Content-Type: application/json' \
--data '{
    "weight": "9",
    "height": "2",
    "width": "2",
    "length": "625",
    "voucherCode": "SENIOR"
}'
```

Assuming no errors, it should produce an output showing the discounted cost.

This service communicates with a third-party API as per the challenge. However, as of this writing (i.e. 2024-02-13) that API is returning HTTP 504 and cannot be accessed. To work around this issue, a mock API is created within the same service that mimics the API contract of the real thing. The scripts above have been configured to run the server and communicate with this mock API.

If there is a need to update the configuration such that it communicates with the actual API, please update the scripts files above and modify the variable `APP_GET_VOUCHER_API` to point to the actual API.

