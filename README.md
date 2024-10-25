[![](https://jitpack.io/v/synaos/vulnerabilities-cache.svg)](https://jitpack.io/#synaos/vulnerabilities-cache)
# Vulnerabilities Cache

Provides an easy way to resolve vulnerabilities by packages and the affected versions.

## Usage

### Java

#### Dependencies

1. Ensure you're using the Maven repository which is serving the packages:
   ```xml
   <repositories>
       <repository>
           <id>jitpack.io</id>
           <url>https://jitpack.io</url>
       </repository>
   </repositories>
   ```
2. Select your dependency:
   ```xml
   <dependencies>
      <!-- Use this if you want to address the cache directly and
           don't use one of the other modules. -->
      <dependency>
         <groupId>com.github.synaos.vulnerabilities-cache</groupId>
         <artifactId>core</artifactId>
         <version>[RELEASE-TAG]</version>
      </dependency>

      <!-- Use this if you want to evaluate Maven dependencies. -->
      <dependency>
         <groupId>com.github.synaos.vulnerabilities-cache</groupId>
         <artifactId>maven</artifactId>
         <version>[RELEASE-TAG]</version>
      </dependency>
   </dependencies>
   ```

