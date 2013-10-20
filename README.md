Sentry Grails Plugin
=========================

[![Stories in Ready](https://badge.waffle.io/benorama/grails-sentry.png?label=ready)](http://waffle.io/benorama/grails-sentry)

# Introduction

The **Sentry Plugin** allows you to integrate [Sentry](http://www.getsentry.com) in your [Grails](http://grails.org) application for exception error tracking and aggregation.

It provides the following Grails artefacts:
* **SentryService** - A [Raven Java](https://github.com/kencochrane/raven-java) server side client to explicitly capture messages or exceptions.
* **SentryTagLib** - A collection of tags to use [Raven JS](http://support.kissmetrics.com/apis/javascript) client for JS exception tracking in your GSPs.


# Installation

Declare the plugin dependency in the BuildConfig.groovvy file, as shown here:

```groovy
grails.project.dependency.resolution = {
		inherits("global") { }
		log "info"
		repositories {
				//your repositories
		}
		dependencies {
				//your dependencies
		}
		plugins {
				//here go your plugin dependencies
				runtime ':sentry:0.1'
		}
}
```


# Config

Create a [Sentry](http://www.getsentry.com) account, in order to get your own _dsn_.

Add your Sentry site _dsn_ to your _grails-app/conf/Config.groovy_:

```groovy
grails.plugin.sentry.dsn = {DSN}
grails.plugin.sentry.enabled = true // Default to true for Production
```
By default the Sentry will only be enabled for Production environments.
If you need it to be enabled for other environments, make sure that it is explicitly enabled in your configs.

Config parameters specific to [Raven Java](https://github.com/kencochrane/raven-java):

```groovy
// Async, default is false
grails.plugin.sentry.async = true
// Level threshold, default is Level.WARN
grails.plugin.sentry.threshold = Level.ALL
```

Config parameters specific to [Raven JS](http://support.kissmetrics.com/apis/javascript):

```groovy
// An array of error messages that should not get passed to Sentry. You'll probably want to set this to ["Script error."]
grails.plugin.sentry.ignoreErrors = ["Script error."]
// An array of regular expressions matching urls which will not get passed to Sentry. For example, you could set it to [/ajax\.googleapis\.com\/ajax\/libs\/jquery/i] to ignore errors from the Google Hosted jQuery library.
grails.plugin.sentry.ignoreUrls = [/ajax\.googleapis\.com\/ajax\/libs\/jquery/i]
// The logger name you wish to send with the message. Defaults to 'javascript'.
grails.plugin.sentry.logger = 'yoursite.errors.javascript'
```

Option config parameters for JS.

```groovy
grails.plugin.sentry.enabled = true
```


To test if plugin is correctly configured, you can generate the following exception:
TODO

# Usage

TODO

# Sentry SSL Certificate Issue

TODO

_app.getsentry.com_ SSL certificate provider might not be valid by default.
When Sentry client will try to capture an exception, you will get the infamous **SunCertPathBuilderException: Unable To Find Valid Certification Path To Requested Target** exception.

To add a Sentry SSL certificate on OSX:

```bash
sudo keytool -importcert -alias getsentry -file getsentry.cer -keystore /Library/Java/Home/lib/security/cacerts
```

By default, the certificate password will either be _changeme_ or _changeit_.

# Latest releases

* 2012-12-12 **V0.1.0** : initial release

# Bugs

To report any bug, please use the project [Issues](http://github.com/benorama/grails-sentry/issues) section on GitHub.


# Beta status

This is a **beta release**.
