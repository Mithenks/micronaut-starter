package io.micronaut.starter.feature.database

import io.micronaut.starter.BeanContextSpec
import io.micronaut.starter.application.ApplicationType
import io.micronaut.starter.feature.Features
import io.micronaut.starter.feature.build.gradle.templates.buildGradle
import io.micronaut.starter.feature.build.maven.templates.pom
import io.micronaut.starter.options.BuildTool
import io.micronaut.starter.options.Language
import io.micronaut.starter.options.TestFramework

import java.util.stream.Collectors

class TestContainersSpec extends BeanContextSpec {

    void "test oracle dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'oracle']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:oracle-xe")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test mysql dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mysql']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:mysql")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test postgres dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'postgres']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:postgresql")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test mariadb dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mariadb']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:mariadb")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test sqlserver dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'sqlserver']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:mssqlserver")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test mongo-reactive dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mongo-reactive']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:mongodb")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test mongo-sync dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mongo-sync']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:mongodb")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test mongo-gorm dependency is present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(),
                getFeatures(['testcontainers', 'mongo-gorm'], Language.GROOVY), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:mongodb")')
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "test testcontainers core is present when no testcontainer modules are present for gradle"() {
        when:
        String template = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers']), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:testcontainers")')
    }

    void "testframework dependency is present for gradle for feature #feature and spock framework"() {
        when:
        def template = buildGradle.template(ApplicationType.DEFAULT, buildProject(),
                getFeatures([feature], Language.DEFAULT_OPTION, TestFramework.SPOCK), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:spock")')

        where:
        feature << ["mongo-reactive", "mongo-sync", "mysql", "postgres", "mariadb", "sqlserver", "oracle"]
    }

    void "testframework dependency is present for gradle for feature #feature and junit framework"() {

        when:
        def template = buildGradle.template(ApplicationType.DEFAULT, buildProject(),
                getFeatures([feature], Language.DEFAULT_OPTION, TestFramework.JUNIT), false).render().toString()

        then:
        template.contains('testImplementation("org.testcontainers:junit-jupiter")')

        where:
        feature << ["mongo-reactive", "mongo-sync", "mysql", "postgres", "mariadb", "sqlserver", "oracle"]
    }

    void "test oracle dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'oracle']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>oracle-xe</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test mysql dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mysql']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>mysql</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test postgres dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'postgres']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>postgresql</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test mariadb dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mariadb']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>mariadb</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test sqlserver dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'sqlserver']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>mssqlserver</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test mongo-reactive dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mongo-reactive']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>mongodb</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test mongo-sync dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', 'mongo-sync']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>mongodb</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test mongo-gorm dependency is present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(),
                getFeatures(['testcontainers', 'mongo-gorm'], Language.GROOVY), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>mongodb</artifactId>
      <scope>test</scope>
    </dependency>
""")
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "test testcontainers dependency is present and no testcontainer modules are present for maven"() {
        when:
        String template = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers']), []).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>testcontainers</artifactId>
      <scope>test</scope>
    </dependency>
""")
    }

    void "testframework dependency is present for maven for feature #feature and spock framework"() {
        when:
        def template = pom.template(ApplicationType.DEFAULT, buildProject(),
                getFeatures([feature], Language.DEFAULT_OPTION, TestFramework.SPOCK, BuildTool.MAVEN,ApplicationType.DEFAULT),[]).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>spock</artifactId>
      <scope>test</scope>
    </dependency>
""")

        where:
        feature << ["mongo-reactive", "mongo-sync", "mysql", "postgres", "mariadb", "sqlserver", "oracle"]
    }

    void "testframework dependency is present for maven for feature #feature and junit framework"() {

        when:
        def template = pom.template(ApplicationType.DEFAULT, buildProject(),
                getFeatures([feature], Language.DEFAULT_OPTION, TestFramework.JUNIT, BuildTool.MAVEN,ApplicationType.DEFAULT),[]).render().toString()

        then:
        template.contains("""
    <dependency>
      <groupId>org.testcontainers</groupId>
      <artifactId>junit-jupiter</artifactId>
      <scope>test</scope>
    </dependency>
""")

        where:
        feature << ["mongo-reactive", "mongo-sync", "mysql", "postgres", "mariadb", "sqlserver", "oracle"]
    }

    void "test there is a dependency for every non embedded driver feature"() {
        when:
        String mavenTemplate = pom.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', driverFeature.getName()]), []).render().toString()
        String gradleTemplate = buildGradle.template(ApplicationType.DEFAULT, buildProject(), getFeatures(['testcontainers', driverFeature.getName()]), false).render().toString()

        then:
        gradleTemplate.contains("org.testcontainers")
        mavenTemplate.contains("org.testcontainers")

        where:
        driverFeature <<  beanContext.streamOfType(DatabaseDriverFeature)
                .filter({ f ->  !f.embedded() })
                .collect(Collectors.toList())
    }

    void "test all non embedded drivers apply the test containers feature"() {
        when:
        Features features = getFeatures([driverFeature.getName()])

        then:
        features.contains("testcontainers")

        where:
        driverFeature <<  beanContext.streamOfType(DatabaseDriverFeature)
                .filter({ f ->  !f.embedded() })
                .collect(Collectors.toList())
    }
}
