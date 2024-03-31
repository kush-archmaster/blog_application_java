package com.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.constants.BlogApplicationConstant;
import com.blog.entities.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repositories.PostRepository;
import com.blog.services.FileImageService;

@Service
public class FileImgServiceImpl implements FileImageService{
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public String uploadImg(String path, MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
		/* create random id to avoid duplicate file name */
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		String filePath = path + File.separator + fileName1;
		
		/* create folder if not created */
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, Long postId) throws FileNotFoundException {
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException(BlogApplicationConstant.POST, BlogApplicationConstant.ID, postId));
		if(post.getImg()!=null) {
			String fullPath = path + File.separator + post.getImg();
			InputStream is = new FileInputStream(fullPath);
			return is;
		}
		else throw new ResourceNotFoundException(BlogApplicationConstant.IMG, BlogApplicationConstant.ID, postId);
	}

}
