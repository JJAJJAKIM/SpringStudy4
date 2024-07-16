package com.web.study01;

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

import java.util.Map;

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
	
}
