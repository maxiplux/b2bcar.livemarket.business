package livemarket.business.b2bcart.controllers;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import livemarket.business.b2bcart.models.files.FileStorage;
import livemarket.business.b2bcart.services.FileStorageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Secured({
        "ROLE_ADMIN",
        "ROLE_USER"
})
@RequestMapping("/api/v1/file-storage")
public class FileStorageController {

    @Autowired
    private FileStorageServices fileStorageServices;


    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer "), @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")})
    @GetMapping("/download/stream/{id}")
    public void streamVideo(@PathVariable String id, HttpServletResponse response) throws IllegalStateException, IOException {
        FileStorage video = fileStorageServices.getFileById(id);
        FileCopyUtils.copy(video.getStream(), response.getOutputStream());
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer "), @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")})
    @PostMapping("/")
    public ResponseEntity<FileStorage>  addVideo(@RequestParam("title") String title, @RequestParam("file") MultipartFile file, Model model) throws IOException {

        FileStorage fileStorage = fileStorageServices.addFileToStorage(title+'-'+file.getName(), file);
        return new ResponseEntity<FileStorage>(fileStorage, HttpStatus.OK);
    }

}
