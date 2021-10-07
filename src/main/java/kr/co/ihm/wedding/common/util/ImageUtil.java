package kr.co.ihm.wedding.common.util; /**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ImageUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * MultipartFile 객체를 File 객체로 변환
     * @param multipartFile
     * @param path
     * @return
     * @throws IOException
     */
    public static File multipartFileToFile(MultipartFile multipartFile, String path) throws IOException { 
        
        final File file = new File(path);
        file.createNewFile();
        
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        
        return file;
    }

    /**
     * 파일 move
     * @param srcPath
     * @param trgPath
     * @return
     */
    public static boolean moveFile(String srcPath, String trgPath) {

        File srcFile = new File(srcPath);
        File trgFile = new File(trgPath);

        return moveFile(srcFile, trgFile);
    }

    /**
     * 파일 move
     * @param srcFile
     * @param trgFile
     * @return
     */
    public static boolean moveFile(File srcFile, File trgFile) {

        // Directory 생성
        File trgDir = new File(FilenameUtils.getFullPath(trgFile.getPath()));
        if (!trgDir.exists()) {
            trgDir.mkdirs();
        }

        boolean bMove = false;
        if (srcFile.exists()) {
            bMove = srcFile.renameTo(trgFile);
        }

        return bMove;
    }

    /**
     * 이미지 파일 to Base64 인코딩
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String convertImageFileToBase64(String path) throws FileNotFoundException, IOException {

        File file = new File(path);
        return convertImageFileToBase64(file);
    }

    /**
     * 이미지 파일 to Base64 인코딩
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String convertImageFileToBase64(File file) throws FileNotFoundException, IOException {

        String base64 = null;
        final String path = file.getAbsolutePath();
        final String fileExt = FilenameUtils.getExtension(path).toLowerCase();

        logger.info("path : {}", path);

        if (file.exists()) {

            try (FileInputStream inputStream = new FileInputStream(file);
                 ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();) {
                
                int len = 0;
                byte[] buf = new byte[1024];

                while ((len = inputStream.read(buf)) != -1) {
                    byteOutputStream.write(buf, 0, len);
                }

                byte[] fileArray = byteOutputStream.toByteArray();
                String chgByte = new String(Base64.encodeBase64(fileArray));
                base64 = "data:image/"+ fileExt +";base64, " + chgByte;

            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("FILE_NOT_FOUND :" + path);
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        } else {
            throw new FileNotFoundException("FILE_NOT_FOUND :" + path);
        }

        return base64;
    }

    /**
     * 이미지 파일 로드 <img src> 태그용
     * @param path
     * @param response
     * @return
     * @throws IOException
     */
    public static boolean loadImageInResponseStream(String path, HttpServletResponse response) throws IOException {

        File file = new File(path);
        return loadImageInResponseStream(file, response);
    }

    /**
     * 이미지 파일 로드 <img src> 태그용
     * @param file
     * @param response
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean loadImageInResponseStream(File file, HttpServletResponse response) throws FileNotFoundException, IOException {

        final String path = file.getAbsolutePath();
        
        if (file.exists()) {

            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                 BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());) {
                
                byte[] byteImgArray = new byte[(int)file.length()];
                int read = 0;
			
                while ((read = in.read(byteImgArray)) != -1) {
                    out.write(byteImgArray, 0, read);
                }
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("FILE_NOT_FOUND : " + path);
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        } else {
            throw new FileNotFoundException("FILE_NOT_FOUND : " + path);
        }

        return true;
    }
}
