package com.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileImageService {

	String uploadImg(String path, MultipartFile file) throws IOException;
	InputStream getResource(String path, Long postId) throws FileNotFoundException;
}
