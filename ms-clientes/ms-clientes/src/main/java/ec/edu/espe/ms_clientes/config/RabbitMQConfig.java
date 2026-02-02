package ec.edu.espe.ms_clientes.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "notifications.exchange";
    public static final String QUEUE_NAME = "notifications.queue";
    public static final String ROUTING_KEY = "notifications.routingkey";

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue notificationQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Binding notificationBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    @SuppressWarnings("deprecation") // <--- ESTA LÍNEA SILENCIA EL ERROR
    public MessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();

        // Esto arregla el error de las fechas (LocalDateTime)
        mapper.registerModule(new JavaTimeModule());

        // Esto usa el convertidor de Jackson 2 (aunque esté deprecated, es el que necesitas)
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}