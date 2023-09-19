package com.skypro.SQl_HW33.controller;

import com.skypro.SQl_HW33.dto.AvatarDto;
import com.skypro.SQl_HW33.model.Avatar;
import com.skypro.SQl_HW33.service.AvatarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> save(@RequestParam Long studentId, @RequestBody MultipartFile multipartFile) {
        try {
            Long avatarId = avatarService.save(studentId, multipartFile);
            return ResponseEntity.ok(avatarId);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value = "/from-disk/{id}")
    public void getFromDisk(@PathVariable("id") Long id, HttpServletResponse response){
        Avatar avatar = avatarService.getById(id);
        response.setContentType(avatar.getMediaType());
        response.setContentLength((int) avatar.getFileSize());
        try {
            FileInputStream fis = new FileInputStream(avatar.getFilePath());
            fis.transferTo(response.getOutputStream());
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/from-db/{id}")
    public ResponseEntity<byte[]> getFromDB(@PathVariable("id") Long id){
        Avatar avatar = avatarService.getById(id);
        byte[] data = avatar.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getFileSize());
        return ResponseEntity.status(200).headers(headers).body(data);
    }
    @GetMapping("/page/{num}")
    public List<AvatarDto> getAll(@RequestParam ("pageNumber") Integer pageNumber,
                                  @RequestParam ("pageSize") Integer pageSize ){
        return avatarService.getAll(pageNumber,pageSize);
    }
}
