package other;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

/**
 * author: ZGF
 * 05-2020/5/11 : 10:14
 * context :
 */

public class TarTarGzFile {

    public static void main(String[] args) throws IOException {
        InputStream is = null;
        TarArchiveEntry tarEntry;
        GzipCompressorInputStream gis = null;
        TarArchiveInputStream zis = null;

        try {
            is = new FileInputStream("C:/Users/Shinelon/Desktop/sqlite3.tar.gz");
            gis = new GzipCompressorInputStream(is);
            zis = new TarArchiveInputStream(gis);

            while ((tarEntry = zis.getNextTarEntry()) != null) {
                File file = new File("D:/YouKu/" + tarEntry.getName());
                OutputStream out;
                try{
                    out = new FileOutputStream(file);
                    int length;
                    byte[] b = new byte[1024];
                    while ((length = zis.read(b)) != -1) {
                        out.write(b, 0 ,length);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (gis != null) {
                try {
                    gis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
