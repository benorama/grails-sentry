import grails.util.Environment
import net.kencochrane.raven.log4j.SentryAppender
import org.apache.log4j.Level
import org.apache.log4j.Logger

class SentryGrailsPlugin {

    def version = "0.1.RC1"
    def grailsVersion = "2.0 > *"

    def author = "Benoit Hediard"
    def authorEmail = "ben@benorama.com"
    def title = "Sentry Plugin"
    def description = "The Sentry Plugin allows your Grails application to use Sentry exception error tracking and aggregation."

    def documentation = "https://github.com/benorama/grails-sentry"
    def license = "APACHE"
    def organization = [ name: "AgoraPulse", url: "http://www.agorapulse.com/" ]
    def issueManagement = [ system: "github", url: "https://github.com/benorama/grails-sentry/issues" ]
    def scm = [  url: "https://github.com/benorama/grails-sentry" ]

    def doWithApplicationContext = { applicationContext ->
        def config = application.config.grails?.plugin?.sentry
        if (config && config.dsn && isEnabled(config)) {
            def appender = new SentryAppender(sentryDsn: config.dsn, threshold: config.threshold ?: Level.WARN)
            appender.activateOptions()
            Logger.rootLogger.addAppender(appender)
        }
    }

    // PRIVATE

    private static boolean isEnabled(config) {
        boolean configEnabled = false
        if (config) {
            // default enabled for PROD
            configEnabled = (Environment.current == Environment.PRODUCTION)

            // if config specified, use that instead
            if (config.containsKey('enabled')) {
                configEnabled = config.enabled
            }
        }
        configEnabled
    }

}
