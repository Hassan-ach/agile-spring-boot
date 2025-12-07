package com.ensa.agile.application.user.security;

public interface IPasswordEncoder {

    public String encode(final CharSequence pass);

    public boolean matches(final CharSequence rawPass, final String encodedPass);
}
