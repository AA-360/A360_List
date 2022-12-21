package com.automationanywhere.botcommand.samples.commands.utils;

import com.automationanywhere.botcommand.exception.BotCommandException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class File {

    public static final String UTF8_BOM = "\uFEFF";

    public File(){}

    public ArrayList<String> readFile(String file){
        ArrayList<String> buffer = new ArrayList<String>();
        try {
//            FileReader fr = new FileReader(file);
//            Scanner sc = new Scanner(fr);
//            while (sc.hasNextLine())
//                buffer.add(sc.nextLine());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file),"UTF-8"));


            String content = Files.readString(Paths.get(file), StandardCharsets.UTF_8);
            for(String line: content.split("\n")){
                buffer.add(this.removeUTF8BOM(line));
            }

        }catch (IOException e) {
            throw new BotCommandException("Erro ao ler arquivo:" + e.getMessage());
        }
        return buffer;
    }

    public void WritetoFile(String file, List<String> text){
        String content = "";
        Charset charset = StandardCharsets.UTF_8;
        Path path;
        String buffer[];
        path = Paths.get(file);
        content = String.join("\n",text);

        try {
            Files.write(path, content.getBytes(charset));
        }catch (IOException e) {
            throw new BotCommandException("Erro ao gravar no arquivo");
        }

    }
    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }
}
