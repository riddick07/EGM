package com.gipermarket.util;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * Weak Encryption algorithm used in MS Classic implemented in Java
 */
public class BitPasswordEncoder implements PasswordEncoder
{

    /**
     * Encodes a clear-text password with an algorthm that matches the hashing
     * strategy used internally within .
     * 
     * <p>
     * The interface specification indicates that this method may throw a
     * DataAccessException; however, this implementation does not throw this
     * exception.
     * 
     * @param rawPass Clear-text password
     * @param salt Salt value for hashing algorithm 
     * @return An encoded version of the password.
     * @throws IllegalArgumentException if rawPass parameter is {@code null}, a
     *             zero-length string or consists of all whitespace.
     */
    public String encodePassword(String rawPass, Object salt)
    {
        if(!StringUtils.hasText(rawPass))
        {
            throw new IllegalArgumentException("Raw password value is either null or zero-length");
        }

        StringBuilder encryptedStr = new StringBuilder();
        for(int i = 0; i < rawPass.length(); ++i)
        {
            char c = rawPass.charAt(i);
            int ascii = (int) c;
            int new_ascii = 32 + (int) (((ascii * (i + 1)) + (i + 1)) % 94);
            encryptedStr.append((char) new_ascii);
        }
        return encryptedStr.toString();
    }

    /**
     * Compares unencoded and encoded versions of password to determine if they
     * represent the same value.
     * 
     * <p>
     * The interface specification indicates that this method may throw a
     * DataAccessException; however, this implementation does not throw this
     * exception.
     * 
     * @param encPass The encoded password
     * @param rawPass The unencoded password
     * @param salt Salt value to fro hashing algorthm (not currently used in
     *            )
     * @return {@code true} if password is valid, otherise {@code false}
     * @throws IllegalArgumentException if either password parameter is
     *             {@code null}, a zero-length string or consists of all
     *             whitespace.
     */
    public boolean isPasswordValid(String encPass, String rawPass, Object salt)
    {
        if(!StringUtils.hasText(rawPass))
        {
            return false;
        }
        if(!StringUtils.hasText(encPass))
        {
            throw new IllegalArgumentException("Encoded password value is either null or zero-length");
        }

        return (encPass.equals(encodePassword(rawPass,salt)));
    }

}
