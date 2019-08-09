package xyz.frt.serverfile.service;

import org.springframework.stereotype.Service;
import xyz.frt.serverfile.entity.File;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author phw 937855602@qq.com
 * create on 2019/8/9 下午3:47
 */
@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Override
    public List<File> list(String basePath, Integer deep) {
        try {
            Files.walkFileTree(Paths.get(basePath), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("NIO preVisitDirectory: "+ dir.toString());
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("NIO postVisitDirectory: "+ dir.toString());
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("NIO visitFile: "+ file.toString());
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println("NIO visitFileFailed: "+ file.toString());
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
