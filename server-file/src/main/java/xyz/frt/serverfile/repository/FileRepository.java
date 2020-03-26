package xyz.frt.serverfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.frt.servercommon.entity.File;

import java.util.List;

/**
 * @author phw 937855602@qq.com
 * create on 2019/10/8 上午9:44
 */
@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByFileNameLike(String filename);

    File findByPathAndFileName(String path, String filename);

    List<File> findAllByPathLike(String pathLike);
}
