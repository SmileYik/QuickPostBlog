package tk.smileyik.quickpost.service;

import tk.smileyik.quickpost.entity.SimpleAlbum;

import java.util.List;

public interface ISimpleAlbumService {
  SimpleAlbum getSimpleAlbumById(String blog, String id);
  List<SimpleAlbum> getAllSimpleAlbums(String blog);
}
