package app.scheduled;

import com.github.raffaeleragni.apilab.apilab.executors.Scheduled;
import javax.inject.Inject;

/**
 * This is part of the executors module, remove this class if you don't use that module.
 */
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
