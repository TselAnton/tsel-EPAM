package convector;

import java.sql.Date;

public interface Convector<T, V> {

    T convert(V obj);
}
