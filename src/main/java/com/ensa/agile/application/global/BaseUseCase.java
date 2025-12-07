package com.ensa.agile.application.global;

public interface BaseUseCase<T, D> {

    D execute(T data);
}
