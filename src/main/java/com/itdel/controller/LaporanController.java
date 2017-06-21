package com.itdel.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itdel.model.Laporan;
import com.itdel.model.User;
import com.itdel.service.LaporanService;
import com.itdel.service.UserService;

@Controller
public class LaporanController {
	
	@Autowired
	UserService userService;
	@Autowired
	LaporanService laporanService;
	
	private String UPLOADED_FOLDER =  "C://Users//Jayuk//Documents//Test Spring//SpringBootSecurity//src//main//resources//images//developer//";
	
	@PreAuthorize("hasRole('ROLE_DEVELOPER')")
	@RequestMapping("/developer/index")
	public ModelAndView developerHome()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String singleFileUpload(@Valid Laporan laporan, @RequestParam("fileImage")MultipartFile[] filesImage,@RequestParam("fileLaporan")MultipartFile fileLaporan, RedirectAttributes redirectAttributes,Principal principal)
	{
		String currentPrincipalName = principal.getName();
		User user = userService.findUserByEmail(currentPrincipalName);
		Path path;
		
		
		List<String> listNameFile =  new ArrayList<>();
		if (fileLaporan.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file Laporan to upload");
			return "redirect/:upload";
		}
		try {
			byte[] bytes = fileLaporan.getBytes();
			path = Paths.get(UPLOADED_FOLDER + "/"+user.getName() +"/");
			Files.createDirectories(path);
			path = Paths.get(UPLOADED_FOLDER + "/"+user.getName() +"/" +fileLaporan.getOriginalFilename());
			laporan.setFileLaporan(fileLaporan.getOriginalFilename());
			Files.write(path, bytes);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		try {
			
			for(MultipartFile file : filesImage)
			{
				byte[] bytes = file.getBytes();
				path = Paths.get(UPLOADED_FOLDER + "/"+user.getName() +"/" +file.getOriginalFilename());
				Files.write(path, bytes);
				listNameFile.add(" "+file.getOriginalFilename().toString());
				laporan.setImageList(laporan.getImageList()+" "+file.getOriginalFilename());
			}
			redirectAttributes.addFlashAttribute("message", "You succesfully uploaded "+ listNameFile.toString());
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		laporan.setRoles(user.getRoleAsString());
		laporan.setUser(user);
		laporan.setTanggalLaporan(new Date());
		laporanService.saveLaporan(laporan);
		return "redirect:/upload";
	}
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView uploadPage(Principal principal)
	{
		ModelAndView modelAndView = new ModelAndView();
		Laporan laporan = new Laporan();
		modelAndView.addObject("laporan", laporan);
		modelAndView.setViewName("upload");
		return modelAndView;
	}
}