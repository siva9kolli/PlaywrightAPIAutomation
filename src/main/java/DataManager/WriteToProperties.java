package DataManager;

import java.io.*;
import java.util.Properties;

public class WriteToProperties {
    Properties prop;
    Properties p;
    public void writeDataToProperties(String key, String value){
        FileReader reader = null;
        FileWriter writer = null;

        File file = new File("./token.properties");

        try {
            reader = new FileReader(file);
            writer = new FileWriter(file);

            p = new Properties();
            p.load(reader);

            p.setProperty(key,value);
            p.store(writer,"write a file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProperty(String key, String value){
        prop.setProperty(key, value);
    }

    public void flush() throws FileNotFoundException, IOException {
        try (final OutputStream outputstream
                     = new FileOutputStream("application.properties");) {
            prop.store(outputstream,"File Updated");
            outputstream.close();
        }
    }
}
