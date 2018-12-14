package com.vitaly.h.newsletterparserweb;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
class EncryptorParserService {

    private String inputField;
    private String outputField;
    private String encryptionKey = System.getenv("encryptionKey");


    void runParser(ParserModel parserModel) {

        inputField = parserModel.getOriginalText();
        outputField = "";
        parseTextFromInputField();
    }

    private void parseTextFromInputField() {
        String key;
        String valueToEncrypt;
        String value;
        String application;
        String modifiedLineOfText;

        for (String lineOfTextFromInputField : inputField.trim().split("\\n")) {
            if (lineOfTextFromInputField.contains("PASSWORD")) {
                String arrayWithAllFields[] = lineOfTextFromInputField.split("values \\(");
                modifiedLineOfText = arrayWithAllFields[1];
                String arrayWithCleanData[] = modifiedLineOfText.split(",");
                key = arrayWithCleanData[0];
                valueToEncrypt = removeFirstAndLastChar(arrayWithCleanData[3]);

                if (isAlreadyEncrypted(valueToEncrypt))
                    value = valueToEncrypt;
                else
                    value = encryptValue(valueToEncrypt);

                application = arrayWithCleanData[5];

                outputField = outputField + "UPDATE properties SET VALUE = '" + value + "' where key = " + key + " and application = " + application + ";" + "\n";
            }
        }
    }

    private String removeFirstAndLastChar(String dirtyString) {
        return dirtyString.substring(1, dirtyString.length() - 1);
    }

    private boolean isAlreadyEncrypted(String checkPassword) {
        try {
            decryptValue(checkPassword);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private String encryptValue(String valueToEncrypt) {
        if (valueToEncrypt == null) {
            return null;
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(valueToEncrypt.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String decryptValue(String valueToDecrypt) {
        if (valueToDecrypt == null) {
            return null;
        }

        try {
            SecretKeySpec secretKey = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(valueToDecrypt)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void wipeTheData() {
        inputField = "";
        outputField = "";
    }

    String getOutputField() {
        return outputField;
    }

    String getInputField() {
        return inputField;
    }


}

