package tk.smileyik.quickpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.smileyik.quickpost.service.IImageService;
import tk.smileyik.quickpost.util.Result;

import java.io.IOException;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月07日 11:16
 */
@RestController
@RequestMapping("/picture")
public class ImageUploadController {

  private IImageService imageService;

  @Autowired
  public void setImageService(IImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping
  public Result<String> uploadPicture(
      @RequestParam MultipartFile img
  ) throws IOException {
    return new Result<>(imageService.uploadImage(img.getBytes()));
  }

  @DeleteMapping
  public Result<Boolean> deletePicture(
      @RequestBody String filename
  ) {
    return new Result<>(imageService.deleteImage(filename));
  }
}
