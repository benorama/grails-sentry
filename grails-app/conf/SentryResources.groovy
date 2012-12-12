modules = {
    'sentry-js' {
        dependsOn 'jquery'
        resource url: [plugin: 'sentry', dir: 'js', file: 'raven-0.6.min.js'], disposition: 'head'
    }
}