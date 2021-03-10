/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admon.bss.util;

import java.net.URLDecoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class SuperBoletosCryptiOS {

    //********METODOS PARA USAR EN SUPER BOLETOS****************//
    /**
     * Encripta string en UTF8 regresando un string en base64
     *
     */
    public static String AES128EncryptText(String textToEncrypt, String key) throws Exception {

        return SuperBoletosCryptiOS.transform(Cipher.ENCRYPT_MODE, textToEncrypt, key);
    }

    /**
     * Desencripta string en base64 regresando un string en UTF8
     *
     */
    public static String AES128DecryptText(String textToDecrypt, String key) throws Exception {

        return SuperBoletosCryptiOS.transform(Cipher.DECRYPT_MODE, textToDecrypt, key);
    }

    //*******FIN METODOS PARA USAR EN SUPER BOLETOS*************//
    //**********METODO DE LA ENCRIPCION (YA INCLUYE LA CODIFICACION BASE64)
    private static String transform(int encryptOrDecrypt, String textToOperate, String keyString) throws Exception {
        String resultString = "";
        try {
            byte[] messageBytes;

            if (Cipher.ENCRYPT_MODE == encryptOrDecrypt) {
                messageBytes = textToOperate.getBytes();
            } else {
//                messageBytes = SuperBoletosEncryptUtils.decode64(textToOperate.getBytes("UTF-8"));/*Original*/
                messageBytes = Base64.decodeBase64(textToOperate.getBytes());
//                messageBytes = Base64.decodeBase64(textToOperate);
            }

            // Se setea la llave secreta a 16 bytes
            byte[] keyAES = new byte[16];//= 
            byte[] keyFromString = keyString.getBytes();

            for (int i = 0; i < keyAES.length; i++) {
                keyAES[i] = 0;
                if (keyFromString.length > i) {
                    keyAES[i] = keyFromString[i];
                }
            }

            SecretKeySpec skeySpec = new SecretKeySpec(keyAES, "AES");

            // Se instancia el cifrador en el modo indicado por el parametro encryptOrDecrypt
            Cipher cipher = Cipher.getInstance("AES"); /*ORIGINAL*/
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(encryptOrDecrypt, skeySpec);

            byte[] operatedBytes = cipher.doFinal(messageBytes);


            if (encryptOrDecrypt == Cipher.DECRYPT_MODE) {
                resultString = new String(operatedBytes, Aes.utf8charset);
            } else {
//                resultString = SuperBoletosEncryptUtils.encode64(operatedBytes); /*original*/
                resultString = Base64.encodeBase64URLSafeString(operatedBytes);
            }
        } catch (Exception e) {

            resultString = "Error al desencriptar dev1: " + textToOperate;
        }


        return resultString;
    }

}
