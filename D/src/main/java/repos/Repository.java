package repos;

public interface Repository<T> {
    Integer ins(T t);
    T read(Integer id);
    Integer update(T t);
    Integer delete(T t);
}
