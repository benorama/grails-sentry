package grails.sentry

import grails.util.Environment
import net.kencochrane.raven.Client

class SentryService {

    static transactional = false

    def grailsApplication
    Client _client

    def captureException(Throwable exception) {
        client.captureException(exception)
    }

    def captureMessage(String message) {
        client.captureMessage(message)
    }

    // PRIVATE

    private Client getClient() {
        if (!_client) {
            _client = new Client(sentryDsn: config.dsn)
        }
        _client
    }

    private def getConfig() {
        grailsApplication.config.grails?.plugin?.sentry
    }

    private boolean isEnabled() {
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
