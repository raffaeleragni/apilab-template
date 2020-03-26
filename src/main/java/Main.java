
import static com.github.raffaeleragni.apilab.appconfig.Env.Vars.API_ENABLE_CONSUMERS;
import static com.github.raffaeleragni.apilab.appconfig.Env.Vars.API_ENABLE_SCHEDULED;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    System.setProperty(API_ENABLE_CONSUMERS.name(), "true");
    System.setProperty(API_ENABLE_SCHEDULED.name(), "true");
    var app = DaggerApplicationComponent.builder().build().application();
    app.start();
  }
}
