package com.jdyx.es;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@SpringBootApplication
@EnableScheduling
@EnableSwagger2WebMvc
@PropertySources({
        @PropertySource(value = "classpath:config.properties", ignoreResourceNotFound = true)
})
public class EsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsServerApplication.class, args);
    }
}
