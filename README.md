# poll_app

* Works at least in the IDE

## TODO

### React

* Add test cases
* Fix possible bugs
* Upgrade to React 18.x

### Spring

* Add test cases
* Fix possible bugs
* Upgrade to Spring Boot 3.x

### Gradle

* Fix: bootJar does not work properly for the html/js content
  * Problem: Task `bootJar` does not recognise newly created files in `resources` folder (`dependsOn` does not help)
  * Workaround: Run task `copyReactApp` before task `bootJar`

### AWS

* Configure as Beanstalk app
* Configure in ECS