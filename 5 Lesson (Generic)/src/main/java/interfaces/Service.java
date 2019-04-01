package interfaces;

import java.util.List;

public interface Service<T> {
    T getEntity();
    List<T> getAllEnitys();
    void saveEnity(T enity);
    void saveMultipleEnitys(List<T> enitys);
}
