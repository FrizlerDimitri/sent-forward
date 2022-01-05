package com.oth.sentforward.Utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Collection;

@Configuration
public class SentForwardUtilsConfig {

    @Bean
    public SimpleDateFormat getSDF()
    {
       return new SimpleDateFormat("MM.dd.yyyy, hh:mm");
    }



}
