package com.api.v1.onboarding.documentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SpringFoxConfig {

    // http://localhost:8080/swagger-ui/#/
    @Bean
    fun api() : Docket =
        Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.api.v1.onboarding"))
            .paths(PathSelectors.any())
            .build()

}