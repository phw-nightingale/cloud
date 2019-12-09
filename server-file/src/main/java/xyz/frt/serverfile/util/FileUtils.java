package xyz.frt.serverfile.util;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2019/12/9 下午3:38
 */
public class FileUtils {

    /**
     *
     * @param contentType
     * @return
     */
    public static String parseFileType(String contentType) {
        String type = "other";
        switch (contentType) {
            case "text/plain":
                type = "text";
                break;

            case "application/vnd.rar":

            case "application/x-compressed-tar":

            case "application/zip":
                type = "zip";
                break;

            case "video/mp4":
                type = "mov";
                break;

            case "image/png":
                type = "img";
                break;

            case "audio/mpeg":
                type = "mp3";
                break;
        }
        return type;
    }

}
