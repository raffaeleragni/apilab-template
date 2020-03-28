
import com.github.raffaeleragni.apilab.scheduled.Scheduled;
import javax.inject.Inject;

public class MyScheduled implements Scheduled {

  @Inject MyEventService event;
  
  @Inject
  public MyScheduled() {
  }

  @Override
  public long period() {
    return 1000;
  }
  
  @Override
  public void run() {
    event.send("ciao");
  }
  
}
