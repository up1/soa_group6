package com.fileinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by super on 7/3/2560.
 */

@CrossOrigin
@Controller
@RestController
public class FileInfoController {

    private final FileInfoRepository fileInfoRepository;

    @Autowired
    public FileInfoController(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @RequestMapping(value = "/filesinfo/{id}")
    public FileInfo getFilesinfo(@RequestParam(value="fileinfoid", defaultValue="1") int fileinfoid) {
        return this.fileInfoRepository.findById((Integer) fileinfoid);
    }
}
