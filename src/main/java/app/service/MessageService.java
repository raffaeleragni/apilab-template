package app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.raffaeleragni.apilab.queues.QueueService;
import com.rabbitmq.client.ConnectionFactory;
import javax.inject.Inject;

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
