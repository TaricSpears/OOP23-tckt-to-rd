package it.unibo.controller.readercontroller.api;

public interface ReaderController<T> {
    T read();

    String getFilePath();
}
