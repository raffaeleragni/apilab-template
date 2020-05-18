package app;

import com.github.raffaeleragni.apilab.apilab.executors.ExecutorsModule;
import com.github.raffaeleragni.apilab.core.ApplicationInstance;
import com.github.raffaeleragni.apilab.core.ApplicationModule;
import com.github.raffaeleragni.apilab.jdbi.JdbiModule;
import com.github.raffaeleragni.apilab.rabbitmq.RabbitMQModule;
import com.github.raffaeleragni.apilab.rest.RESTModule;
import javax.inject.Singleton;

@dagger.Component(modules = {
  // Our own app module providing custom instances
  AppModule.class,
  // API-LAB modules
  ApplicationModule.class,
  RESTModule.class,
  JdbiModule.class,
  RabbitMQModule.class,
  ExecutorsModule.class})
@Singleton
public interface AppComponent {
  ApplicationInstance instance();
}
