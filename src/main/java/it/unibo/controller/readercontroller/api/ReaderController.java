package it.unibo.controller.readercontroller.api;

/**
 * This interface models a generic reader
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
