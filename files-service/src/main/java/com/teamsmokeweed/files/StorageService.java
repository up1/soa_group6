package com.teamsmokeweed.files;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by jongzazaal on 23/4/2560.
 */
@Service
public interface StorageService {

    void init() throws IOException;

    void store(MultipartFile file) throws IOException;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename) throws MalformedURLException;

    void deleteAll();

}