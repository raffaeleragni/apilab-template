
import com.github.raffaeleragni.apilab.appconfig.Application;
import com.github.raffaeleragni.apilab.appconfig.ApplicationConfig;
import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {ApplicationConfig.class, ComponentsModule.class})
public interface ApplicationComponent {
  Application application();
}
