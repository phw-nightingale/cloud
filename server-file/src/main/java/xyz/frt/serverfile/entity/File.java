package xyz.frt.serverfile.entity;

import xyz.frt.serverfile.util.ContentType;

/**
 * @author phw 937855602@qq.com
 * create on 2019/8/9 下午3:07
 *
 * 磁盘文件类型
 */
public class File {

    private String fileName;

    private Integer size;

    private String fileType;

    private ContentType contentType;

    private String path;

    private boolean isFolder;

    private boolean isFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }
}
