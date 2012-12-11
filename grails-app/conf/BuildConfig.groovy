grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    inherits 'global'
    log 'warn'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        runtime 'commons-codec:commons-codec:1.6' // Required for raven-java (1.5 provided by default in Grails 2.2)
        runtime 'com.googlecode.json-simple:json-simple:1.1' // Required for raven-java
    }
    plugins {
        build(':release:2.2.0', ':rest-client-builder:1.0.3') {
            export = false
        }
    }
}