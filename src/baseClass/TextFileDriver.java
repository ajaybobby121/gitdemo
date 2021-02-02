package baseClass;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileDriver {

    /**
     * This class shows how to write file in java
     * @param args
     * @throws IOException 
     */
    //public static String WriteExcelTab
	public static String WriteText(String data , int noOfLines, String path ) {
        //String ret_Message = ""; 
        
		writeUsingFileWriter(data,path);
        
        //writeUsingBufferedWriter(data, noOfLines, path);
        //writeUsingFiles(data, path);
        //writeUsingOutputStream(data, path);
        System.out.println(" WriteText DONE");
        return "SUCCESS";
    }

    /**
     * Use Streams when you are dealing with raw data
     * @param data
     */
    private static void writeUsingOutputStream(String data, String path) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Use Files class from Java 1.7 to write files, internally uses OutputStream
     * @param data
     */
    private static void writeUsingFiles(String data, String path) {
        try {
            Files.write(Paths.get(path), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use BufferedWriter when number of write operations are more
     * It uses internal buffer to reduce real IO operations and saves time
     * @param data
     * @param noOfLines
     */
    private static void writeUsingBufferedWriter(String data, int noOfLines, String path) {
        File file = new File(path);
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine=data+System.getProperty("line.separator");
        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            for(int i = noOfLines; i>0; i--){
                br.write(dataWithNewLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Use FileWriter when number of write operations are less
     * @param data
     */
    private static void writeUsingFileWriter(String data, String path) {
        File file = new File(path);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
  //Akshaya :create html file  in the system
  	public static void writeToHtml(String html) throws Exception{
  		 FileOutputStream outputStream = new FileOutputStream("D://FileGenerated.html");
  		 byte[] strToBytes = html.getBytes();
  		 outputStream.write(strToBytes);
  		 
  		 System.out.println("HTML FILE GENERATED SUCCESSFULLY");
  		 outputStream.close();
  		
  	} 
  	
  	
  	

}