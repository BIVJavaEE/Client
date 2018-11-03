package business;

public abstract class BusinessCheck<T> {
    public abstract void run(T context) throws BusinessException;
}
