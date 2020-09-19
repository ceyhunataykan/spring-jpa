package com.ceyhunataykan.UserJPA.controller;

import com.ceyhunataykan.UserJPA.entity.User;
import com.ceyhunataykan.UserJPA.exception.GenericNotFoundException;
import com.ceyhunataykan.UserJPA.exception.ResponseMessage;
import com.ceyhunataykan.UserJPA.helper.ExcelUpload;
import com.ceyhunataykan.UserJPA.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@Api(value = "Users Api Documentation")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "User List Metod")
    @GetMapping("/list")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "User Find Id Metod")
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<User> getFindById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getFindById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Excel List Download Metod")
    @GetMapping("/list/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "users.xlsx";
        InputStreamResource file = new InputStreamResource(userService.excelOutput());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @ApiOperation(value = "Excel List Upload Metod")
    @PostMapping("/list/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelUpload.hasExcelFormat(file)) {
            try {
                userService.excelInput(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @ApiOperation(value = "User Add Metod")
    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "User Update Metod")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody User user, @PathVariable Integer id) {
        userService.update(user, id);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @ApiOperation(value = "User Delete Metod")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws GenericNotFoundException {
        userService.delete(id);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

}