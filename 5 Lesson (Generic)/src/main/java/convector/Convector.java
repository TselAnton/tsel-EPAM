package convector;

import java.util.List;

public interface Convector<T, S> {
    S conOneEnity(T dto);
    List<S> conManyEnities(List<T> dto);
}
