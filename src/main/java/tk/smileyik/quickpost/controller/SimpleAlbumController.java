package tk.smileyik.quickpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.smileyik.quickpost.entity.SimpleAlbum;
import tk.smileyik.quickpost.service.ISimpleAlbumService;
import tk.smileyik.quickpost.util.Result;

import java.util.List;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 10:46
 */
@CrossOrigin
@RestController
@RequestMapping("simpleAlbum")
public class SimpleAlbumController {
  private ISimpleAlbumService simpleAlbumService;

  @Autowired
  public void setSimpleAlbumService(ISimpleAlbumService simpleAlbumService) {
    this.simpleAlbumService = simpleAlbumService;
  }

  @GetMapping("{blogId}")
  public Result<List<SimpleAlbum>> allSimpleAlbum(@PathVariable("blogId") String blogId) {
    return new Result<>(simpleAlbumService.getAllSimpleAlbums(blogId));
  }

  @GetMapping("{blogId}/{albumId}")
  public Result<SimpleAlbum> allSimpleAlbum(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId
  ) {
    return new Result<>(simpleAlbumService.getSimpleAlbumById(blogId, albumId));
  }
}
