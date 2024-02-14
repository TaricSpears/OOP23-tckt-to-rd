package it.unibo.controller.readercontroller.impl;

import it.unibo.controller.readercontroller.api.ReaderController;

public abstract class AbstractReaderController<T> implements ReaderController<T> {
    private final String path;

    public AbstractReaderController (final String filePath){
        this.path = filePath;
    }

    @Override
    public abstract T read();

    @Override
    public String getPath() {
        return this.path;
    }
}
