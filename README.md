# appium-lambdatest-sample

This repository contains sample project for running Appium tests for Android and iOS locally and on LambdaTest cloud
platform.

## Prerequisites

You must have Java JDK 11 installed on your machine.

For running the tests on LambdaTest cloud platform, you must have a valid LambdaTest account.
Also, you must save your username and access key in the environment variables of your `.zshrc` / `.bash_profile`. If you
are on Windows, you must save your API key and secret in the System environment variables.

```shell
export LT_USERNAME=<your-username>
export LT_ACCESS_KEY=<your-access-key>
```

## Run the tests

You can run the tests by running the following command:

```bash
> mvn clean install
```

You can also run from the IDE by opening `testng.xml` file and right-clicking on it and selecting `Run`.
