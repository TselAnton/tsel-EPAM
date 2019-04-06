package repository.inter;

import java.util.List;

public interface Repository<T> {
    T getEntity();
    List<T> getAllEnitys();
    void saveEnity(T enity);
    void saveMultipleEnitys(List<T> enitys);
}
