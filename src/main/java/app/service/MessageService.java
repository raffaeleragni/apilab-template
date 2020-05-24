package app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.raffaeleragni.apilab.queues.QueueService;
import com.rabbitmq.client.ConnectionFactory;
import javax.inject.Inject;

/**
 * This is part of the rabbitmq module and the queued async services.
 * Remove this class if you don't use that module.
 */
public class MessageService extends QueueService<String> {

  @Inject
  public MessageService(ConnectionFactory connectionFactory, ObjectMapper mapper) {
    super(connectionFactory, mapper, "messages", String.class);
  }

  @Override
  public void receive(String message) {
    System.out.println("Hello: "+message);
  }

}
