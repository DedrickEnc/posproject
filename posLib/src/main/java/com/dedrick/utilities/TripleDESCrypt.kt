package com.dedrick.utilities

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object TripleDESCrypt {
    fun encrypt(input: ByteArray, password: ByteArray): ByteArray {
        val c = Cipher.getInstance("DESede/ECB/NoPadding")
        val keySpec = SecretKeySpec(password, "DESede")
        c.init(Cipher.ENCRYPT_MODE, keySpec)
        return c.doFinal(input)
    }

    fun decrypt(input: ByteArray, password: ByteArray): ByteArray {
        val c = Cipher.getInstance("DESede/ECB/NoPadding")
        val keySpec = SecretKeySpec(password, "DESede")
        c.init(Cipher.DECRYPT_MODE, keySpec)
        return c.doFinal(input)
    }
}