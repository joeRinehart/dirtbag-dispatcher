package org.github.joerinehart

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant

class HelloWorldSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared RxHttpClient httpClient = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void "We can say hi and pass a name"() {
        setup:
        String now = Instant.now().toString()

        when:
        String result = httpClient.toBlocking()
                .retrieve(HttpRequest.POST('/hello-world', [name: now]))

        then:
        result == "Hello, $now!"
    }
    
}
