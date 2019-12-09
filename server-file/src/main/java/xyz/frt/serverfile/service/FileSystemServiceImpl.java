package xyz.frt.serverfile.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import xyz.frt.servercommon.entity.ContentType;
import xyz.frt.servercommon.entity.File;
import xyz.frt.servercommon.entity.User;
import xyz.frt.serverfile.excepiton.FileExistsException;
import xyz.frt.serverfile.excepiton.FileNotFoundException;
import xyz.frt.serverfile.excepiton.FileSystemException;
import xyz.frt.serverfile.repository.FileRepository;
import xyz.frt.serverfile.util.ApplicationContextProvider;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author phw 937855602@qq.com
 * create on 2019/8/9 下午3:47
 */
@Slf4j
@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Value("${spring.servlet.multipart.location}")
    private String basePath;

    private final FileRepository fileRepository;

    public FileSystemServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> list(Path dir) {
        assert dir != null;
        List<File> files = new ArrayList<>();
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
            for (Path path : stream) {
                File file = new File();
                if (Files.isDirectory(path)) {
                    file.setIsDirectory(1);
                    file.setFileType(ContentType.DIR.name());
                }
                file.setFileName(path.getFileName().toString());
                file.setPath(path.getParent().toString());
                file.setSize(Files.size(path));
                //System.out.println(path.getFileName());
                files.add(file);
            }
        } catch (IOException e) {
            if (e instanceof NoSuchFileException) {
                throw new FileNotFoundException("File or Directory NOT FOUND!");
            } else {
                e.printStackTrace();
            }
        }

        return files;
    }

    @Override
    public List<File> list(String path) {
        assert path != null && !path.equals("");
        Path dir = Paths.get(path);
        return list(dir);
    }

    @Override
    @Transactional
    public File upload(MultipartFile file, String path) {
        assert file != null;
        File upload = new File();
        upload.setSize(file.getSize());
        upload.setFileName(file.getOriginalFilename());
        upload.setIsDirectory(0);
        upload.setContentType(file.getContentType());
        upload.setPath(path.concat(java.io.File.separator).concat(file.getOriginalFilename()));

        User user = ApplicationContextProvider.getCurrentUser();
        path = basePath.concat(java.io.File.separator).concat(user.getUsername()).concat(path);
        log.info("File will upload on path:" + path);
        try {
            Path p = Paths.get(path);
            if (!Files.exists(p)) {
                Files.createDirectories(p);
            }
            path = path.concat(java.io.File.separator).concat(upload.getFileName());
            p = Paths.get(path);
            if (Files.exists(p)) {
                throw new FileExistsException("File name has already exists");
            }
            Files.createFile(p);
            file.transferTo(p);
        } catch (IOException e) {
            throw new FileSystemException("File transfer failed:".concat(e.getMessage()));
        }
        return fileRepository.saveAndFlush(upload);
    }

    @Override
    public File mkdir(String parent, String name) {
        Assert.notNull(parent, "Path parent cannot be null");
        Assert.notNull(name, "Directory name cannot be null");
        User user = ApplicationContextProvider.getCurrentUser();
        String p = basePath.concat(java.io.File.separator).concat(user.getUsername()).concat(parent).concat(java.io.File.separator).concat(name);
        try {
            Path path = Paths.get(p);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            } else {
                throw new FileExistsException("Directory named " + name + " has already exists");
            }

        } catch (IOException e) {
            throw new FileSystemException("Make directories failed:" + e.getMessage());
        }
        File dir = new File();
        dir.setFileName(name);
        dir.setFileType("Directory");
        dir.setSize(0L);
        dir.setPath(p);
        return dir;
    }

    @Override
    public void download(String path) {
        Assert.notNull(path, "Download path cannot be null");
        path = basePath
                .concat(java.io.File.separator).concat(ApplicationContextProvider.getCurrentUser().getUsername())
                .concat(path);
        Path p = Paths.get(path);
        if (!Files.exists(p)) {
            throw new FileNotFoundException("Download file cannot be found");
        }


        FileChannel inChannel = null;
        //字节码缓冲区
        ByteBuffer buffer = null;
        //第二缓冲区
        byte[] bytes = null;
        try {
            int buffSize = (int) Files.size(p);
            inChannel = FileChannel.open(p);
            buffer = ByteBuffer.allocateDirect(buffSize);
            bytes = new byte[buffSize / 6];
            HttpServletResponse response = ApplicationContextProvider.getResponse();
            OutputStream out = ApplicationContextProvider.getResponse().getOutputStream();
            //正常显示中文文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(p.getFileName().toString(), "UTF-8"));
            response.setContentType("application/octet-stream");

            while (inChannel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    int offset = Math.min(buffer.remaining(), buffSize / 6);
                    buffer.get(bytes, 0, offset);
                    out.write(bytes);
                }
                buffer.clear();
            }
            log.debug("Download successful");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (buffer != null) {
                    buffer.clear();
                }
            } catch (IOException e) {
                throw new FileSystemException(e.getMessage());
            }
        }
    }

    @Override
    public void download(Long id) {
        Assert.notNull(id, "ID cannot be null");
        Optional<File> opt = fileRepository.findById(id);
        Assert.isTrue(opt.isPresent(), "Download file with ID: " + id + " cannot be found");
        download(opt.get().getPath());
    }

    @Override
    public File remove(Long id) {
        Assert.notNull(id, "Remove File ID cannot be null");
        File file = fileRepository.getOne(id);
        Assert.notNull(file, "Remove File ID: " + id + " cannot be found");
        //删除物理文件
        String path = basePath
                .concat(java.io.File.separator)
                .concat(ApplicationContextProvider.getCurrentUser().getUsername())
                .concat(file.getPath());
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            throw new FileSystemException(e.getMessage());
        }
        //删除数据库记录
        fileRepository.deleteById(id);
        return file;
    }

    @Override
    public List<File> search(String filename) {
        Assert.notNull(filename, "Search content cannot be empty");
        return fileRepository.findByFileNameLike("%".concat(filename).concat("%"));
    }
}
