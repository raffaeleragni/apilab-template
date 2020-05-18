package app.scheduled;

import com.github.raffaeleragni.apilab.apilab.executors.Scheduled;
import javax.inject.Inject;

public class ScheduledSeconds implements Scheduled {

  @Inject
  public ScheduledSeconds() {
    ///
  }

  @Override
  public long period() {
    return 1000;
  }

  @Override
  public void run() {
    System.out.println("1 second passed.");
  }

}
