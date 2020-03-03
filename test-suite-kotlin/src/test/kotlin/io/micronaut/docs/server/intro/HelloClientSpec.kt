package io.micronaut.docs.server.intro

// tag::imports[]
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.context.annotation.Property
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject
// end::imports[]
import io.micronaut.test.extensions.kotlintest.MicronautKotlinTestExtension

/**
 * @author graemerocher
 * @since 1.0
 */
@Property(name = "spec.name", value = "HelloControllerSpec")
// tag::class[]
@MicronautTest // <1>
class HelloClientSpec : StringSpec() {

    @Inject
    lateinit var client: HelloClient // <2>

    init {
// end::class[]
        //Not needed in documentation, because will be called automatically when ProjectConf set in kotlin test
        MicronautKotlinTestExtension.instantiate(HelloClientSpec::class)    //initializes test context
        MicronautKotlinTestExtension.beforeSpecClass(this, emptyList()) //injects variables
        MicronautKotlinTestExtension.beforeSpec(this)
// tag::class[]
        "testHelloWorldResponse"() {
            client.hello().blockingGet().shouldBe("Hello World")
        }
    }
}
// end::class[]