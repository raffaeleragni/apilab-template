package app.endpoint;

import com.github.raffaeleragni.apilab.rest.Endpoint;
import io.javalin.Javalin;
import io.javalin.http.Context;
import javax.inject.Inject;

public class GetEndpoint implements Endpoint {

  @Inject
  public GetEndpoint() {
    ///
  }

  @Override
  public void register(Javalin javalin) {
    javalin.get("/hello", this);
  }

  @Override
  public void handle(Context ctx) throws Exception {
    ctx.result("Hello");
  }

}
