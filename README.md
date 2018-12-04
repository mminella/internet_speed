# Internet Speed Tracker

This project uses https://fast.com to test periodically my internet speed.
It polls fast.com's API once every 30 minutes and stores the results in a local MySql database.
Since there is no java client for the fast.com API, I'm using the javascript one provided via the [fast-speedtest-api CLI](https://www.npmjs.com/package/fast-speedtest-api).

To execute the project, build the project using `./mvnw clean install`.
Once the project has been built, you can execute it via `$ java -jar target/internet_speed-0.0.1-SNAPSHOT.jar &`.

Standard Spring Boot configurations apply.  The application.properties found in this project is what I'm using but feel free to modify as needed.
Everything in this code is hard coded (I'm lazy).  PRs are welcome to clean it up.

## TODO
* Add a test that uses https://speedtest.net as a validation mechanism.