package com.ry.a03;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server")
@Data
public class Bean4 {

    private int port;

    private String shutdown;

}
