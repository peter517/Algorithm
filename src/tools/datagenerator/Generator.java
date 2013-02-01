package tools.datagenerator;

public interface Generator<T> {
     public T next();
     public T next(int topLimit) ;
     
     public T[] nextArray(int length);
     public T[] nextArray(int length,int topLimit);
     
}
