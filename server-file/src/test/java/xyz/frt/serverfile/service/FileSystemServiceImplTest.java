package xyz.frt.serverfile.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.frt.serverfile.ServerFileApplicationTests;

/**
 * @author phw 937855602@qq.com
 * create on 2019/8/9 下午3:57
 */
public class FileSystemServiceImplTest extends ServerFileApplicationTests {

    @Autowired
    private FileSystemService fileSystemService;

    @Test
    public void testList() {
        fileSystemService.list("/home/phw/Documents", 0);
    }


}
