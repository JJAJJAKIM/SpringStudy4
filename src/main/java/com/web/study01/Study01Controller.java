package com.web.study01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.components.FileComponent;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/s1")
public class Study01Controller {

	@GetMapping("/")
	public String index() {
		return "s1/index";
	}
	
	@Autowired
	private FileComponent fc;
	
	@PostMapping("/fileUpload")
	public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
		ra.addAttribute("url", fc.upload(file));
	    return "redirect:/s1/view";
	}
	
	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam("url") String url) {
		return fc.getFile(url);
	}

	@PostMapping("/file")
	public String file(@RequestParam("file") MultipartFile file){
		Map<String, Object> map = fc.setFile(file);
		System.out.println(map);
		return null;
	}
	@PostMapping("/test1")
	public String test1(@RequestParam("file") MultipartFile file){
		String filename = file.getOriginalFilename();
		long filesize = file.getSize();
		String contenttype = file.getContentType();
		log.info("filename : {}", filename);
		log.info("filesize : {}", filesize);
		log.info("contenttype : {}", contenttype);

		//파일 저장 경로가 필요하기 때문에 생성해야한다.
		String path = "/Users/chaseongmin/Downloads/upload/2024-07-16/";
		try {
			File f = new File(path+filename);
			f.mkdirs(); // 파일 저장할 경로 생성
			file.transferTo(f); // 파일 복사내용 생성
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
