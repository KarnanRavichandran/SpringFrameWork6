package com.timothy.config;


import com.timothy.GuessCount;
import com.timothy.MaxNumber;
import com.timothy.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.timothy")
@PropertySource("classpath:config/game.properties")
public class GameConfig {
    @Value("${game.maxNumber}")
    private int maxNumber;
    @Value("${game.guessCount}")
    private int guessCount;

    @Value("${game.minNumber:5}")
    private int minNumber;

    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }
    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }
    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }


}
