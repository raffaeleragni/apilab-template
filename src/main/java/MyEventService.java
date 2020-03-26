
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.raffaeleragni.apilab.queues.QueueService;
import com.rabbitmq.client.ConnectionFactory;
import javax.inject.Inject;

public class MyEventService extends QueueService<String> {

  @Inject
  public MyEventService(ConnectionFactory rabbitFactory, ObjectMapper mapper) {
    super(rabbitFactory, mapper, "my-queue-name", String.class);
  }

  @Override
  public void receive(String message) {
    System.out.println("MESSAGE: "+message);
  }

}
