package pe.com.empresa.util;

public class GenericCrud<T> {

    public T t;

    public void create(T t) {
        this.t = t;
    }

    public T get() {
        return this.t;
    }
}
