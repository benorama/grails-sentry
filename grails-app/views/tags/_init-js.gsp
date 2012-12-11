<%@ page import="grails.converters.JSON" %>
<r:require module="sentry-js"/>
<r:script disposition="head">
    // Sentry Raven client
    Raven.config({
        <g:if test="${fetchHeaders}">fetchHeaders: ${fetchHeaders},</g:if>
        <g:if test="${ignoreErrors}">ignoreErrors: ${ignoreErrors as JSON},</g:if>
        <g:if test="${ignoreUrls}">ignoreUrls: ${ignoreUrls as JSON},</g:if>
        <g:if test="${logger}">logger: "${logger}",</g:if>
        <g:if test="${signatureUrl}">signatureUrl: "${signatureUrl}",</g:if>
        projectId: ${projectId},
        publicKey: "${publicKey}",
        servers: ["${server}"]
    });
    window.onerror = Raven.process;
</r:script>