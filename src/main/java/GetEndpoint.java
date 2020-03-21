
import com.github.raffaeleragni.apilab.appconfig.Endpoint;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import javax.inject.Inject;

public class GetEndpoint implements Endpoint {

  @Inject
  public GetEndpoint() {
  }
  
  @Override
  public void register(Javalin javalin) {
    javalin.get("/api/test", this);
  }

  @OpenApi(responses = {
    @OpenApiResponse(status = "200")
  })
  @Override
  public void handle(Context ctx) throws Exception {
    ctx.result("Hello universe.");
  }
  
}