
import com.github.raffaeleragni.apilab.appconfig.ApplicationInitializer;
import com.github.raffaeleragni.apilab.appconfig.Endpoint;
import com.github.raffaeleragni.apilab.appconfig.ImmutableApplicationInitializer;
import com.github.raffaeleragni.apilab.queues.QueueService;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import static java.util.Collections.emptySet;
import java.util.Set;
import javax.inject.Singleton;

@dagger.Module
public class ComponentsModule {
  
  @Provides
  @Singleton 
  public ApplicationInitializer initalizer() {
    return ImmutableApplicationInitializer.builder()
      .build();
  }
  
  @Provides
  @IntoSet 
  public Endpoint endpoint(GetEndpoint endpoint) {
    return endpoint;
  }
  
  @Provides
  @IntoSet 
  public QueueService consumer(MyEventService consumer) {
    return consumer;
  }
  
}
