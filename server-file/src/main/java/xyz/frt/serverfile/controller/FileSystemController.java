package xyz.frt.serverfile.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping("/files")
    public JsonResult upload(@RequestParam MultipartFile file, @RequestParam String path) {
        return JsonResult.success(fileSystemService.upload(file, path));
    }

    @GetMapping("/files/{id}")
    public void download(@PathVariable Long id) {
        fileSystemService.download(id);
    }

    @DeleteMapping("/files/{id}")
    public JsonResult remove(@PathVariable Long id) {
        return JsonResult.success(fileSystemService.remove(id));
    }

    @GetMapping("/files/filename/{filename}")
    public JsonResult search(@PathVariable String filename) {
        return JsonResult.success(fileSystemService.search(filename));
    }

}
