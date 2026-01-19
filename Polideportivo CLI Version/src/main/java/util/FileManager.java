package util;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileManager<T> {



    public static <T> void saveFile(String fileName, T object){
        try {
            OutputStream fos = Files.newOutputStream(Paths.get(fileName));
            ObjectOutputStream escribir = new ObjectOutputStream(fos);

            escribir.writeObject(object);

            escribir.close();
            fos.close();

        } catch (Exception e) {
            PrintStream out = System.err;
            out.println("Error al escribir en el archivo. "
                    + e.getMessage());
        }
    }

    public static <T> T loadFile(String fileName){
        T object = null;
        try {

            InputStream fis = Files.newInputStream(Paths.get(fileName));
            ObjectInputStream reader;

            while(fis.available()>0){
                reader= new ObjectInputStream(fis);
                T element= (T) reader.readObject();
                object = element;
            }

        } catch (Exception e) {
            PrintStream out = System.err;
            out.println("Error al escribir en el archivo. "
                    + e.getMessage());
        }
        return object;
    }


    public static void saveFileList(String fileName, List<?> objects){
        try (
            OutputStream fos = Files.newOutputStream(Paths.get(fileName));
            ObjectOutputStream escribir = new ObjectOutputStream(fos);
        ) {
            
            escribir.writeObject(objects);

        } catch (Exception e) {
            PrintStream out = System.err;
            out.println("Error al escribir en el archivo. "
                    + e.getMessage());
        }
    }


    public static <T> List<?> loadFileList(String fileName){
        List<T> list = null;
        try {

            InputStream fis = Files.newInputStream(Paths.get(fileName));
            ObjectInputStream reader;

            while(fis.available()>0){
                reader= new ObjectInputStream(fis);
                List<T> element= (List<T>) reader.readObject();
                list = element;
            }

        } catch (Exception e) {
            PrintStream out = System.err;
            out.println("Error al escribir en el archivo. "
                    + e.getMessage());
        }
        return list;
    }

}
