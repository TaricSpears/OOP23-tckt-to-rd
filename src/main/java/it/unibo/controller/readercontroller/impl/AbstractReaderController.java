package it.unibo.controller.readercontroller.impl;

import it.unibo.controller.readercontroller.api.ReaderController;

/*
 * abstract implementation of {@link ReaderController}, a generic
 * reader controller.
 */
public abstract class AbstractReaderController<T> implements ReaderController<T> {
    
    private final String path;

    /*
     * Initalizes the new AbstractReaderController
     * 
     * @param filePath the path of file to be read
     */
    protected AbstractReaderController (final String filePath){
        this.path = filePath;
    }

    /*
     * @return the result of the reading operation
     */
    @Override
    public abstract T read();

    /*
     * @return the path of file to be read
     */
    @Override
    public String getPath() {
        return this.path;
    }
}
