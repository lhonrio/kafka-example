package com.lhonrio.strproducer.config;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {

  public final KafkaProperties properties;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    var config = new HashMap<String, Object>();
    config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
    return new KafkaAdmin(config);
  }

  @Bean
  public KafkaAdmin.NewTopics topics() {
    return new NewTopics(TopicBuilder.name("str-topic").partitions(2).replicas(1).build());
  }
}
