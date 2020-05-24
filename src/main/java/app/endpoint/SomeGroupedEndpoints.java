package app.endpoint;

import com.github.raffaeleragni.apilab.rest.Endpoint;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.OpenApi;
import io.javalin.plugin.openapi.annotations.OpenApiContent;
import io.javalin.plugin.openapi.annotations.OpenApiParam;
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody;
import io.javalin.plugin.openapi.annotations.OpenApiResponse;
import javax.inject.Inject;

public class SomeGroupedEndpoints implements Endpoint {

  @Inject
  public SomeGroupedEndpoints() {
    ///
  }

  @Override
  public void register(Javalin javalin) {
    javalin.get("/hello", this);
    javalin.get("/hello/:var", this::specialHandle);
    // javalin.post("/hello/:var", ...);
    // javalin.put("/hello/:var", ...);
    // javalin.patch("/hello/:var", ...);
    // javalin.delete("/hello/:var", ...);
  }

  @OpenApi(
    summary = "get endpoints, generic",
    description = "",
    responses = {
      @OpenApiResponse(status = "200",
        content = @OpenApiContent(from = String.class))
    }
  )
  @Override
  public void handle(Context ctx) throws Exception {
    ctx.result("Hello");
  }

  @OpenApi(
    summary = "get endpoints, variable",
    description = "",
    pathParams = {@OpenApiParam(name="var", type = String.class)},
    requestBody = @OpenApiRequestBody(
      content = @OpenApiContent(from = String.class)),
    responses = {
      @OpenApiResponse(status = "200",
        content = @OpenApiContent(from = String.class))
    }
  )
  public void specialHandle(Context ctx) {
    ctx.result("Hello: " + ctx.pathParam("var"));
  }

}
