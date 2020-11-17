![Build](https://img.shields.io/badge/Build-Automated-2980b9.svg?style=for-the-badge)
![Latest Version](https://img.shields.io/badge/Latest_Version-v1.0.0-27ae60.svg?style=for-the-badge)
![License](https://img.shields.io/badge/License-Apache_2.0-e74c3c.svg?style=for-the-badge)</br>
![Java CI with Gradle](https://github.com/myConsciousness/fluent-regex/workflows/Java%20CI%20with%20Gradle/badge.svg)


# Fluent Regex

## What is it?

Fluent Regex main repository.
## How To Use

### 1. Add the dependencies

> **_Note:_**<br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/fluent-regex/packages).<br>
> Please contact me for a token to download the package.

**_Maven_**

```xml
<dependency>
  <groupId>org.thinkit.common.regex</groupId>
  <artifactId>fluent-regex</artifactId>
  <version>v1.0.0</version>
</dependency>

<servers>
  <server>
    <id>github</id>
    <username>myConsciousness</username>
    <password>xxxxxxxxxxxxxxxxxx</password>
  </server>
</servers>
```

**_Gradle_**

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/myConsciousness/fluent-regex")
        credentials {
          username = "myConsciousness"
          password = "xxxxxxxxxxxxxxxxxx"
        }
    }
}

dependencies {
    implementation 'org.thinkit.common.regex:fluent-regex:v1.0.0'
}
```

## License

```
Copyright 2020 Kato Shinya.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
```

## More Information

`Fluent Regex` was designed and implemented by Kato Shinya, who works as a freelance developer.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [Creator Website](https://myconsciousness.github.io/)
- [License](https://github.com/myConsciousness/fluent-regex/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/fluent-regex/releases)
- [Package](https://github.com/myConsciousness/fluent-regex/packages)
- [File a Bug](https://github.com/myConsciousness/fluent-regex/issues)
- [Reference](https://myconsciousness.github.io/fluent-regex/)