package app;

public class Main {
  public static void main(String[] args) {
    app.DaggerAppComponent
      .create()
      .instance()
      .start();
  }
}
