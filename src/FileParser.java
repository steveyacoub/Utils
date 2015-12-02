import java.io.*;
import java.util.HashSet;

/**
 * Created by steve on 9/16/15.
 */
public class FileParser {
    public static String newline = System.getProperty("line.separator");
    public static String beginIndexString = "lead_id\"\":";
    public static String endIndexString = ",\"\"user_id";
    public static String inputPath = "/Users/steve/export.tsv";
    public static String outputPath = "/Users/steve/leadids.txt";

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            String currentLine;
            HashSet<String> leads = new HashSet<>();
            br = new BufferedReader(new FileReader(inputPath));
            File file = new File(outputPath);
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            while ((currentLine = br.readLine()) != null) {
                int begin = currentLine.indexOf(beginIndexString) + beginIndexString.length();
                if(begin < 0 ) {
                    continue;
                }
                int end = currentLine.indexOf(endIndexString);
                if(end < 0) {
                    continue;
                }
                String leadId = currentLine.substring(begin, end);
                leads.add(leadId);
            }
            for(String lead :leads) {
                bw.write(lead + ",");
                bw.write(newline);
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
