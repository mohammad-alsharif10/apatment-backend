package com.backend.apartment.service;

import com.backend.apartment.database.ImageRepository;
import com.backend.apartment.dto.ImageDto;
import com.backend.apartment.mapper.ImageMapper;
import com.backend.apartment.model.Image;
import com.backend.apartment.utils.Constants;
import com.skeleton.database.BaseRepository;
import com.skeleton.mapper.BaseMapper;
import com.skeleton.service.BaseService;
import com.skeleton.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ImageService extends BaseService<Long, Image, ImageDto> {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public BaseRepository<Image, Long> getRepository() {
        return imageRepository;
    }

    @Override
    public BaseMapper<Long, ImageDto, Image> getBaseMapper() {
        return imageMapper;
    }

    public Image uploadProfileImage(MultipartFile file) throws IOException {
        StringBuilder profileImagesPath = new StringBuilder()
                .append(Constants.generalImagesPath)
                .append(File.separator)
                .append("profileImages")
                .append(File.separator)
                .append(userDetailsService.username());
        creatDirectoryIfNotExist(profileImagesPath.toString());
        String imagePath = profileImagesPath + File.separator + Instant.now().toString() + file.getOriginalFilename();
        return Image.builder().isCurrentProfileImage(true).path(processSingleImage(file, imagePath)).build();
    }


    public List<Image> uploadApartmentImages(MultipartFile[] imagesList, Long apartmentId) {
        StringBuilder apartmentImagesPath = new StringBuilder()
                .append(Constants.generalImagesPath)
                .append(File.separator)
                .append("apartmentImages")
                .append(File.separator)
                .append(userDetailsService.username())
                .append(apartmentId);
        creatDirectoryIfNotExist(apartmentImagesPath.toString());
        return processImagesListAsync(imagesList, apartmentImagesPath.toString());
    }

    @SneakyThrows
    private List<Image> processImagesListAsync(MultipartFile[] imagesList, String apartmentPath) {

        MultipartFile[] partOne = Arrays.copyOfRange(imagesList, 0, 21);
        MultipartFile[] partTwo = Arrays.copyOfRange(imagesList, 21, 42);
        MultipartFile[] partThree = Arrays.copyOfRange(imagesList, 42, 63);
        MultipartFile[] partFour = Arrays.copyOfRange(imagesList, 63, 84);

        CompletableFuture<List<String>> completableFuturePartOne = CompletableFuture.supplyAsync(() -> {
            ArrayList<String> paths1 = new ArrayList<>();
            for (MultipartFile multipartFile : partOne) {
                try {
                    processListOfImages(multipartFile, paths1, apartmentPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return paths1;
        });

        CompletableFuture<List<String>> completableFuturePartTwo = CompletableFuture.supplyAsync(() -> {
            ArrayList<String> paths2 = new ArrayList<>();
            for (MultipartFile multipartFile : partTwo) {
                try {
                    processListOfImages(multipartFile, paths2, apartmentPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return paths2;
        });

        CompletableFuture<List<String>> completableFuturePartThree = CompletableFuture.supplyAsync(() -> {
            ArrayList<String> paths3 = new ArrayList<>();
            for (MultipartFile multipartFile : partThree) {
                try {
                    processListOfImages(multipartFile, paths3, apartmentPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return paths3;
        });

        CompletableFuture<List<String>> completableFuturePartFour = CompletableFuture.supplyAsync(() -> {
            ArrayList<String> paths4 = new ArrayList<>();
            for (MultipartFile multipartFile : partFour) {
                try {
                    processListOfImages(multipartFile, paths4, apartmentPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return paths4;
        });

        List<CompletableFuture<List<String>>> completableFutureList = Arrays.asList(
                completableFuturePartOne,
                completableFuturePartTwo,
                completableFuturePartThree,
                completableFuturePartFour);
        completableFutureList.add(completableFuturePartOne);
        completableFutureList.add(completableFuturePartTwo);
        completableFutureList.add(completableFuturePartThree);
        completableFutureList.add(completableFuturePartFour);
        return imageRepository
                .saveAll(
                        Arrays.asList(completableFuturePartOne,
                                completableFuturePartTwo,
                                completableFuturePartThree,
                                completableFuturePartFour)
                                .parallelStream()
                                .map(CompletableFuture::join)
                                .flatMap(Collection::stream)
                                .map(path -> Image.builder().path(path).isCurrentProfileImage(false).build())
                                .collect(Collectors.toList()));
    }

    private void processListOfImages(MultipartFile file, List<String> imagesPaths, String apartmentPath) throws IOException {
        String imagePath = apartmentPath + File.separator + Instant.now().toString() + file.getOriginalFilename();
        imagesPaths.add(imagePath);
        File convertFile = new File(imagePath);
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
    }

    private String processSingleImage(MultipartFile file, String imagePath) throws IOException {
        File convertFile = new File(imagePath);
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return imagePath;
    }


    @SneakyThrows
    private void creatDirectoryIfNotExist(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            Path path = Paths.get(directoryPath);
            Files.createDirectories(path);
        }
    }
}
