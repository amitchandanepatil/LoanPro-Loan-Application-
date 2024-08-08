package com.LoanProDevTeam.LoanPro.Services.Impl;

import com.LoanProDevTeam.LoanPro.Exceptions.BadApiRequestException;
import com.LoanProDevTeam.LoanPro.Services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        // Get the original filename from the uploaded file
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename:{}", originalFilename);
// Generate a random UUID as the base file name
        String fileName = UUID.randomUUID().toString();
// Extract the file extension from the original filename
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
// Combine the base file name and the file extension to get the full filename
        String fileNameWithExtension = fileName + extension;
// Combine the file path and the full filename to get the full path
        String fullPathWithFileName = path + fileNameWithExtension;
        logger.info("full image path: {} ", fullPathWithFileName);
// Check if the file extension is one of the allowed types (png, jpg, jpeg)
        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
            // Check if the folder to save the file exists, if not, create it
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            // Save the file to the specified path
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            // Return the full filename with extension as the response
            return fileNameWithExtension;
        } else {
            // If the file extension is not allowed, throw an exception
            throw new BadApiRequestException("File with this " + extension + " not allowed !!");
        }
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        // Combine the path and name to get the full path to the resource
        String fullPath = path + File.separator + name;
        // Create a new FileInputStream with the full path
        InputStream inputStream = new FileInputStream(fullPath);
        // Return the input stream for the resource
        return inputStream;
    }
}
