package xyz.frt.serverfile.excepiton;

/**
 * @author phw 937855602@qq.com
 * create on 2019/9/30 下午3:53
 */
public class FileExistsException extends FileSystemException {

    public FileExistsException(String err) {
        super(err);
    }

}
