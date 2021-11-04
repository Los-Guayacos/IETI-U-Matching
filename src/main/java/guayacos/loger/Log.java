package guayacos.loger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Maneja los logs del sistema en un archivo de texto "log.txt"
 */
public class Log {

    private static BufferedWriter bufferedReader;
    private static String path;
    private static Log instance;

    /**
     * Constructor de la clase
     * @param path
     */
    public Log(String path){
        if(instance == null)
            instance = this;
        Log.path = path;
    }

    /**
     * Constructor de la clase con parametros
     * @param path
     * @param reset
     * @throws IOException
     */
    public Log(String path, boolean reset) throws IOException{
        if(instance == null)
            instance = this;
        Log.path = path;
        open(!reset);
    }

    /**
     * Abre el archivo en modo de escritura agregada
     * @param append Lectura o escritura ?
     * @throws IOException
     */
    private static void open(boolean append) throws IOException{
        bufferedReader = new BufferedWriter(new FileWriter(path, append));
    }

    /**
     * Agrega una linea de texto sobre el archivo con su fecha de registro
     * @param line Linea agregada
     * @throws IOException
     */
    public static void addLine(String line) throws IOException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        String dateFormat = format.format(new Date());
        open(true);
        bufferedReader.write("[" + dateFormat + "]" + line + "\n");
        close();
    }

    /**
     * Obtener los logs
     * @return Logs registrados
     * @throws IOException
     */
    public static String[] getLines() throws IOException{
        ArrayList<String> linesRead = new ArrayList<String>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        while((line = bufferedReader.readLine()) != null){
            linesRead.add(line);
        }

        bufferedReader.close();

        String[] lines = new String[linesRead.size()];

        for(int i=0; i<linesRead.size(); i++){
            lines[i] = linesRead.get(i);
        }

        return lines;
    }

    /**
     * Reinicia los registros (Cada ejecucion)
     * @throws IOException
     */
    public static void resetLog() throws IOException{
        open(false);
        close();
    }

    /**
     * Cierra el archivo
     * @throws IOException
     */
    private static void close() throws IOException {
        bufferedReader.close();
    }


    public BufferedWriter getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedWriter bufferedReader) {
        Log.bufferedReader = bufferedReader;
    }

    public String getPath() {
        return Log.path;
    }

    public void setPath(String path) {
        Log.path = path;
    }

    public static Log getInstance() {
        return instance;
    }

    public void setInstance(Log instance) {
        Log.instance = instance;
    }


}
