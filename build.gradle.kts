import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform") version "2.4.0"
}

group = "com.lizonying.page1"
version = "0.0.1"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.13.2")

    intellijPlatform {
        bundledPlugin("org.jetbrains.plugins.yaml")
        testFramework(TestFrameworkType.Platform)
        intellijIdeaCommunity("2024.1")
    }
}

intellijPlatform {
    pluginConfiguration {
        id = "page1"
        name = "Page1"
        version = "0.0.6"
        description =
            "Page1 is an HTML generation tool that uses YAML-like files to describe HTML structure, allowing easy conversion to HTML files."
        changeNotes =
            """
Allows omitting `html` and `body`.
      """.trimIndent()

        ideaVersion {
            sinceBuild = "223"
            untilBuild = provider { null }
        }
        vendor {
            name = "Li ZongYing"
            email = "lizongying@msn.com"
            url = "https://github.com/lizongying"
        }
    }
    publishing {
        token = System.getenv("PUBLISH_TOKEN")
    }
}

sourceSets {
    main {
        java {
            srcDirs("src/main/gen")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}