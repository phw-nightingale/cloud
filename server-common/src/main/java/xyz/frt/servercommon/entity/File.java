package xyz.frt.servercommon.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * @author phw 937855602@qq.com
 * create on 2019/8/9 下午3:07
 *
 * 磁盘文件类型
 */
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(225) COMMENT '文件名'")
    private String fileName;

    @Column(columnDefinition = "BIGINT COMMENT '文件大小'")
    private Long size;

    @Column(columnDefinition = "VARCHAR(64) COMMENT '文件类型'")
    private String fileType;

    @Column(columnDefinition = "VARCHAR(64) COMMENT '内容类型'")
    private String contentType;

    @Column(columnDefinition = "VARCHAR(225) COMMENT '服务器路径'")
    private String path;

    @Column(columnDefinition = "TINYINT COMMENT '是否文件夹'")
    private Integer isDirectory;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'", insertable = false)
    private Date createTime;

    @Column(columnDefinition = "DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'", insertable = false)
    private Date updateTime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(Integer isDirectory) {
        this.isDirectory = isDirectory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
