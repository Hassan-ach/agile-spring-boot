package com.ensa.agile.application.global.usecase;

public interface IBaseUseCase<T, D> {

  D execute(T data);
}
