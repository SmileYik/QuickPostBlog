package tk.smileyik.quickpost.dao;

import java.io.File;

public interface IImageDao {
  String saveImage(byte[] bytes, String filename);

  boolean deleteImage(String filename);
}
