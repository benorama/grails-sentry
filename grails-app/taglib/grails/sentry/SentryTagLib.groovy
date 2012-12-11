package grails.sentry

import grails.util.Environment

class SentryTagLib {

    static namespace = 'sentry'

    /**
     * Initialize Sentry Raven JS Client
     *
     * @attr fetchHeaders Generate a HEAD request to gather header information to send with the message. This defaults to 'false' because it doesn't work on cross-domain requests.
     * @attr ignoreErrors An array of error messages that should not get passed to Sentry. You'll probably want to set this to ["Script error."]
     * @attr ignoreUrls An array of regular expressions matching urls which will not get passed to Sentry. For example, you could set it to [/ajax\.googleapis\.com\/ajax\/libs\/jquery/i] to ignore errors from the Google Hosted jQuery library.
     * @attr logger The logger name you wish to send with the message. Defaults to 'javascript'.
     * @attr site An optional site name to include with the message.
     * @attr signatureUrl Use a server side url to get a signature for the message. See Raven JS doc in the "Security" section for more details.
     */
    def initJS = { attrs ->
        if (enabled && config.dsn) {
            URL url = new URL(config.dsn)
            Map model = [
                    publicKey: url.userInfo.tokenize(':')[0],
                    server: "${url.protocol}://${url.host}/",
                    projectId: url.path[1..-1]
            ]
            if (config.fetchHeaders && !attrs.containsKey('fetchHeaders')) attrs.fetchHeaders = config.fetchHeaders
            if (config.ignoreErrors && !attrs.containsKey('ignoreErrors')) attrs.ignoreErrors = config.ignoreErrors
            if (config.ignoreUrls && !attrs.containsKey('ignoreUrls')) attrs.ignoreErrors = config.ignoreUrls
            if (config.logger && !attrs.containsKey('logger')) attrs.logger = config.logger
            if (config.site && !attrs.containsKey('site')) attrs.site = config.site
            if (config.signatureUrl && !attrs.containsKey('signatureUrl')) attrs.signatureUrl = config.signatureUrl
            attrs.each { key, value ->
                model[key] = value
            }
            out << render(template: '/tags/init-js', model: model, plugin: 'sentry')
        }
    }

    // PRIVATE

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
