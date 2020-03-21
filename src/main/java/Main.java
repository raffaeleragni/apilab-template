
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    var app = DaggerApplicationComponent.builder().build().application();
    app.start();
  }
}
