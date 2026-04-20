## Plan: Upgrade Gradle project to Spring Boot 4

Safely evolve the current plain Java Gradle setup to Spring Boot 4 by updating the `plugins` and `dependencies` in [build.gradle](/Users/thaiphan/sources/cs489-all/cs489-final-exam/build.gradle), aligning Java compatibility, and using Boot-managed test libraries to avoid version drift. Finish with one fast Gradle check to confirm dependency resolution and build health.

### Steps
1. Review current `plugins` and `dependencies` in [build.gradle](/Users/thaiphan/sources/cs489-all/cs489-final-exam/build.gradle) and note existing JUnit-only setup.
2. Add Spring Boot plugin in `plugins` (`org.springframework.boot` at a pinned `4.0.x` version), keeping `java`.
3. Add/update `java` toolchain to Boot-compatible level (typically Java 21+) in [build.gradle](/Users/thaiphan/sources/cs489-all/cs489-final-exam/build.gradle).
4. Replace manual JUnit BOM/test entries with Boot-managed `spring-boot-starter-test`; add required runtime starter(s) in `dependencies`.
5. Run a quick verification with `./gradlew clean build` (or `./gradlew dependencies --configuration runtimeClasspath`) to confirm plugin + dependency resolution.

### Further Considerations
1. Which Boot target should be pinned: `4.0.0` baseline or latest `4.0.x` patch?
2. Module intent? Option A app (`bootJar` default) / Option B library (`jar` enabled, `bootJar` disabled).
3. Draft for review: confirm starter choice (web, data, or core) before finalizing dependency list.


