package com.visioplanserver.service;

@FunctionalInterface
public interface DropboxActionResolver<T> {

    T perform() throws Exception;

}
