
import com.github.raffaeleragni.apilab.scheduled.Scheduled;
import javax.inject.Inject;

public class MyScheduled implements Scheduled {

  @Inject
  public MyScheduled() {
  }

  @Override
  public String cron() {
    return "* * * * *";
  }

  @Override
  public void run() {
    System.out.println("ciao");
  }
  
}
