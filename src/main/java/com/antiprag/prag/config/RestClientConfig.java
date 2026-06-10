package com.antiprag.prag.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@ConfigurationProperties(prefix = "app.external")
public class RestClientConfig {

    private String certApi;

    public void setCertApi(String certApi){
        this.certApi = certApi;
    }

    @Bean
    RestClient certificateRestClient() {
        return RestClient.builder()
                .baseUrl(certApi)
                .build();
    }
}
