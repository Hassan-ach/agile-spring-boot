package com.ensa.agile.application.epic.exception;

import com.ensa.agile.domain.global.exception.NotFoundException;

public class EpicNotFoundException extends NotFoundException {

    public EpicNotFoundException(String epicId) { super("Epic" , epicId); }
    public EpicNotFoundException() { super("Epic"); }
}
