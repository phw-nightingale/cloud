package xyz.frt.serverfile.service;

import org.springframework.web.multipart.MultipartFile;
import xyz.frt.servercommon.entity.File;

import java.nio.file.Path;
import java.util.List;

/**
 * @author phw 937855602@qq.com
 * create on 2019/8/9 下午3:40
 * <p>
 * 文件系统操作
 * 包括基本的增删改查
 * 文件遍历
 * 文件上传
 * 文件移动
 * 文件复制等
 */
public interface FileSystemService {

    /**
     * 遍历文件
     * @param dir 当前路径
     * @param relativePath relative path
     * @return
     */
    List<File> list(Path dir, String relativePath);

    /**
     * 遍历文件
     * @param path 当前路径
     * @return
     */
    List<File> list(String path);

    /**
     * 单文件上传
     * @param file 上传的文件
     * @param path 云盘位置
     * @return
     */
    File upload(MultipartFile file, String path);

    /**
     * 创建文件夹
     * @param parent
     * @param name
     * @return
     */
    File mkdir(String parent, String name);

    /**
     * 下载文件
     * @param path 文件在云盘中的位置
     * @return
     */
    void download(String path);

    /**
     * 根据文件ID下载文件
     * @param id
     */
    void download(Long id);

    /**
     * 根据文件ID删除文件
     * @param id
     * @return
     */
    File remove(Long id);

    /**
     * 根据文件名查找文件
     * @param filename
     * @return
     */
    List<File> search(String filename);

}
