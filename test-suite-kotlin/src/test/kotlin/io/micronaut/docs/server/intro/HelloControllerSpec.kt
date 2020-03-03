/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.docs.server.intro

// tag::imports[]
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.context.annotation.Property
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject
// end::imports[]
import io.micronaut.test.extensions.kotlintest.MicronautKotlinTestExtension

@Property(name = "spec.name", value = "HelloControllerSpec")
// tag::class[]
@MicronautTest
class HelloControllerSpec : StringSpec() {

    @Inject
    lateinit var server: EmbeddedServer

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    init {
// end::class[]
// Not needed in documentation, because will be called automatically when ProjectConf set in kotlin test
        MicronautKotlinTestExtension.instantiate(HelloControllerSpec::class) //initializes test context
        MicronautKotlinTestExtension.beforeSpecClass(this, emptyList()) //injects variables
// tag::class[]
        "testHelloWorldResponse"() {
            val rsp: String = client.toBlocking()
                    .retrieve("/hello")
            rsp.shouldBe("Hello World")
        }
    }
}
// end::class[]
