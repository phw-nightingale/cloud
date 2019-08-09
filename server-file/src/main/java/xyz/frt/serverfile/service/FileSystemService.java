package xyz.frt.serverfile.service;

import xyz.frt.serverfile.entity.File;

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
     * @param basePath
     * @param deep
     * @return
     */
    List<File> list(String basePath, Integer deep);

}
