package xyz.frt.serverfile.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.frt.servercommon.common.Pager;
import xyz.frt.servercommon.entity.File;
import xyz.frt.serverfile.service.FileSystemService;
import xyz.frt.servercommon.common.JsonResult;

@RestController
public class FileSystemController {

    private final FileSystemService fileSystemService;

    public FileSystemController(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    @GetMapping("/files/list")
    public JsonResult list(@RequestParam String path) {
        return JsonResult.success(fileSystemService.list(path));
    }

    @PostMapping("/files/upload")
    public JsonResult upload(@RequestParam MultipartFile file, @RequestParam String path) {
        return JsonResult.success(fileSystemService.upload(file, path));
    }

    @PostMapping("/files/dir/{name}")
    public JsonResult mkdir(@PathVariable String name, @RequestParam String path) {
        return JsonResult.success(fileSystemService.mkdir(path, name));
    }

    @GetMapping("/files/id/{id}")
    public void download(@PathVariable Long id) {
        fileSystemService.download(id);
    }

    @GetMapping("/files/file")
    public void download(File file) {
        fileSystemService.download(file.getPath(), file.getFileName());
    }

    @DeleteMapping("/files/{id}")
    public JsonResult remove(@PathVariable Long id) {
        return JsonResult.success(fileSystemService.remove(id));
    }

    @GetMapping("/files/filename/{filename}")
    public JsonResult search(@PathVariable String filename) {
        return JsonResult.success(fileSystemService.search(filename));
    }

    @DeleteMapping("/files/filename/{filename}")
    public JsonResult remove(@PathVariable String filename, @RequestParam String path) {
        return JsonResult.success(fileSystemService.remove(path, filename));
    }

    @GetMapping("/files/page/{page}/size/{size}")
    public JsonResult find(Pager pager) {
        return JsonResult.success(fileSystemService.find(pager));
    }

}
