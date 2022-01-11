package com.oth.sentforward.Utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;

@Configuration
public class SentForwardUtilsConfig {

    @Bean
    public SimpleDateFormat getSDF()
    {
       return new SimpleDateFormat("MMMM/dd/yyyy, HH:mm", Locale.ENGLISH);
    }

}
