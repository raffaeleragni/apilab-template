package app.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * This is just a sample data object, just remove.
 */
@Value.Immutable
@JsonSerialize(as = ImmutablePojo.class)
@JsonDeserialize(as = ImmutablePojo.class)
@Value.Style(jdkOnly = true)
public interface  Pojo {
  long id();
  String name();
}

