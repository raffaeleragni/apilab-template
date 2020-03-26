
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
//    System.setProperty("API_ENABLE_CONSUMERS", "true");
    var app = DaggerApplicationComponent.builder().build().application();
    app.start();
  }
}
