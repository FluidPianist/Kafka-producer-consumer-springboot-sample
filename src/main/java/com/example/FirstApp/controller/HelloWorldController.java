package com.example.FirstApp.controller;

import com.example.FirstApp.service.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class HelloWorldController {

    private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    private KafkaProducer kafkaProducer;

    public HelloWorldController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    //http://localhost:8080/api/v1/kafka/publish_hello?username=adeeb&password=adeeb&message=hello
    @GetMapping("/publish_hello")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
        kafkaProducer.sendMessage(message);
        logger.info(String.format("Message sent : %s", message));
        return ResponseEntity.ok("Message sent to the topic");
    }
}
