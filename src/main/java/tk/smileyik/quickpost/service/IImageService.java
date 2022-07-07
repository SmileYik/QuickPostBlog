package tk.smileyik.quickpost.service;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月07日 11:26
 */
public interface IImageService {
  String uploadImage(byte[] bytes);

  boolean deleteImage(String filename);
}
