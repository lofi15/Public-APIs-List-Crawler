package com.apilistcrawler;


import com.apilistcrawler.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiListCrawlerApplication {

    @Bean
    public RestTemplate getRestTempplate(){
        return new RestTemplate();
    }

    @Autowired
    private CrawlerService crawlerService;

    private static ConfigurableApplicationContext configurableApplicationContext=null;

    private static final Logger logger = LoggerFactory.getLogger(ApiListCrawlerApplication.class);

    public static void main(String[] args) {
        configurableApplicationContext=SpringApplication.run(ApiListCrawlerApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        logger.info("crawling started !");
        crawlerService.crawlData();
        logger.info("crawling finished , data fechted !");
    }
}
