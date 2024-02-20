package it.unibo.controller.readercontroller.api;

/**
 * This interface models a generic reader.
 * 
 * @param <T> the type of the result of the reading operation
 */
public interface ReaderController<T> {

    /**
     * @return the result of the reading operation
     */
    T read();

    /**
     * @return the path of file to be read
     */
    String getPath();
}
