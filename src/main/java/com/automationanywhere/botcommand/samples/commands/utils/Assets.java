package com.automationanywhere.botcommand.samples.commands.utils;

import com.automationanywhere.botcommand.exception.BotCommandException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Assets {
    public String codeLibrary(){
        try{
            InputStream inputStream = getFileFromResourceAsStream("library.js");
            String library = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return library;
        }
        catch (Exception e){
            throw new BotCommandException("Error when trying to load Js code library!");
        }
    }
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
