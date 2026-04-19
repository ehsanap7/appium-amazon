# appium-amazon

## Setup

**`src/main/resources/local.properties`** . Placeholder example:

```properties
IOS_UDID=00000000-000A0000EXAMPLE0001
IOS_XCODE_ORG_ID=ABCDE12345
IOS_PLATFORM_VERSION=18.0

ANDROID_UDID=emulator-5554

APPIUM_SERVER_URL=http://127.0.0.1:4723/
```

## Run

```bash
mvn clean test
```

## Reports

```bash
mvn clean test allure:report
allure serve target/allure-results
```