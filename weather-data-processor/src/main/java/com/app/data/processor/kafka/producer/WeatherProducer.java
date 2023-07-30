package com.app.data.processor.kafka.producer;

import com.app.data.processor.domains.WeatherEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherProducer {

    private final NewTopic topic;
    private final KafkaTemplate<String, WeatherEvent> template;

    public void sendMessage(WeatherEvent event) {
        log.info(String.format("Weather event -> %s", event));

        Message<WeatherEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        template.send(message);
    }
}
