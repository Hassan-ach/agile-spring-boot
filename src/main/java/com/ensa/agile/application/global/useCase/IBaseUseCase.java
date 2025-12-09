package com.ensa.agile.application.global.useCase;

public interface IBaseUseCase<T, D> {

  D execute(T data);
}
