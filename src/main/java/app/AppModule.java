package app;

import app.endpoint.GetEndpoint;
import app.scheduled.ScheduledSeconds;
import app.service.MessageService;
import com.github.raffaeleragni.apilab.apilab.executors.Scheduled;
import com.github.raffaeleragni.apilab.core.Env;
import com.github.raffaeleragni.apilab.queues.QueueService;
import com.github.raffaeleragni.apilab.rest.Endpoint;
import com.github.raffaeleragni.apilab.rest.ImmutableRESTInitializer;
import com.github.raffaeleragni.apilab.rest.RESTInitializer;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import static java.util.Collections.emptySet;
import java.util.Set;
import javax.inject.Named;
import javax.inject.Singleton;

@dagger.Module
public class AppModule {

  @Provides
  @Singleton
  public Env env() {
    return new Env();
  }

  @Provides
  @Singleton
  public RESTInitializer restInitializer() {
    return ImmutableRESTInitializer.builder().build();
  }

  @Provides
  @Named("jdbiImmutables")
  public Set<Class<?>> immutableClass() {
    return emptySet();
  }

  @Provides
  @IntoSet
  public Endpoint getEndpoint(GetEndpoint endpoint) {
    return endpoint;
  }

  @Provides
  @IntoSet
  public QueueService mesageService(MessageService service) {
    return service;
  }

  @Provides
  @IntoSet
  public Scheduled scheduledSeconds(ScheduledSeconds scheduled) {
    return scheduled;
  }

}
