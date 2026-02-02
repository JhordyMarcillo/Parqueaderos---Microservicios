package ec.edu.espe.ms_clientes.messaging;

import ec.edu.espe.ms_clientes.model.Persona;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor

public class NotificactionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String ACTION_CREATE = "CREATE";
    private static final String ACTION_UPDATE = "UPDATE";
    private static final String ACTION_DELETE = "DELETE";
    private static final String ENTITY_VEHICULOS = "ENTIDAD VEHICULOS";
    private static final String ENTITY_PERSONAS = "ENTIDAD PERSONA";
    private static final String SEVERITY_INFO = "INFO";
    private static final String SEVERITY_WARN = "WARN";
    private static final String SEVERITY_ERROR = "ERROR";

    public void sendNotification(String action,
                                 String entityType,
                                 UUID entityId,
                                 String message,
                                 String severity,
                                 Map<String, Object> data) {

        NotificationEvent event = NotificationEvent.builder()
                .id(UUID.randomUUID())
                .microservice("microservice - vehiculos, personas")
                .action(action)
                .entityType(entityType)
                .entityId(String.valueOf(entityId))
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(data != null ? data : new HashMap<>())
                .severity(severity)
                .build();

        try {
            log.debug("Sending Notification Event: {}", event);
            rabbitTemplate.convertAndSend("notifications.exchange", "notifications.routingkey", event);
            log.info("Notification enviada: {} - {}", action, event);
        } catch (Exception e){
            log.error("Error enviando Notification Event: {} - {}", action, event, e);

        }
    }

    public void sendAutoFamiliarCreated(UUID id, String placa){
        Map<String, Object> data = new HashMap<>();
        data.put("placa",placa);
        data.put("id",id);
        data.put("fecha",LocalDateTime.now());
        sendNotification(ACTION_CREATE, ENTITY_VEHICULOS, id, placa, SEVERITY_INFO, data);
        log.info("Auto familiar enviada: {} - {}", ACTION_CREATE, id);
    }

    public void sendAutoFamiliarUpdate(UUID id, String modelo){
        Map<String, Object> data = new HashMap<>();
        data.put("id",id);
        data.put("modelo", modelo);
        data.put("fecha",LocalDateTime.now());
        sendNotification(ACTION_UPDATE, ENTITY_VEHICULOS, id, modelo, SEVERITY_WARN, data);
        log.info("Notification enviada: {} - {}", ACTION_UPDATE, id);
    }

    public void sendAutoFamiliarDelete(UUID id, String modelo){
        Map<String, Object> data = new HashMap<>();
        data.put("id",id);
        data.put("fecha",LocalDateTime.now());
        data.put("modelo", modelo);
        sendNotification(ACTION_DELETE, ENTITY_VEHICULOS, id, modelo, SEVERITY_ERROR, data);
    }

    public void sendMotoCreated(UUID id, String placa) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("fecha", LocalDateTime.now());
        data.put("placa", placa);
        data.put("tipo", "MOTO");
        sendNotification(ACTION_CREATE, ENTITY_VEHICULOS, id, placa, SEVERITY_INFO, data);
    }

    public void sendMotoUpdate(UUID id, String color) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("fecha", LocalDateTime.now());
        data.put("nuevoColor", color);
        data.put("tipo", "MOTO");
        sendNotification(ACTION_UPDATE, ENTITY_VEHICULOS, id, color, SEVERITY_WARN, data);
    }

    public void sendMotoDelete(UUID id, String placa) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("fecha", LocalDateTime.now());
        data.put("placa", placa);
        data.put("tipo", "MOTO");

        sendNotification(ACTION_DELETE, ENTITY_VEHICULOS, id, placa, SEVERITY_ERROR, data);
    }

    public void sendPersonaCreated(UUID id, String identificacion, String tipo) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("fecha", LocalDateTime.now());
        data.put("identificacion", identificacion);
        data.put("tipo", tipo);

        sendNotification(ACTION_CREATE, ENTITY_PERSONAS, id, identificacion, SEVERITY_INFO, data);
    }

    public void sendPersonaUpdated(UUID id, String identificacion) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("fecha", LocalDateTime.now());
        data.put("identificacion", identificacion);
        data.put("mensaje", "Datos de persona actualizados");

        sendNotification(ACTION_UPDATE, ENTITY_PERSONAS, id, identificacion, SEVERITY_WARN, data);
    }

    public void sendPersonaDeleted(UUID id, String identificacion) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("fecha", LocalDateTime.now());
        data.put("identificacion", identificacion);
        data.put("mensaje", "Registro eliminado permanentemente");

        sendNotification(ACTION_DELETE, ENTITY_PERSONAS, id, identificacion, SEVERITY_ERROR, data);
    }



}


// crear lo mismo en el microservicio de usuario, es decir notificación para cada entidad, en usuario y vehiculo,aproximadamente 12 operaciones


