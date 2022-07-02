package tk.smileyik.quickpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.smileyik.quickpost.entity.Item;
import tk.smileyik.quickpost.service.IAlbumService;
import tk.smileyik.quickpost.util.Result;

import java.util.List;
import java.util.Map;

/**
 * @author SmileYik
 * @Description TODO
 * @date 2022年07月01日 10:52
 */
@CrossOrigin
@RestController
@RequestMapping("/album")
public class AlbumController {
  private IAlbumService albumService;

  @Autowired
  public void setAlbumService(IAlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("{blogId}/{albumId}/{itemId}")
  public Result<Item> getPost(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @PathVariable("itemId") String itemId
  ) {
    Item item = albumService.getPost(blogId, albumId, itemId);
    if (item == null) {
      return new Result<>(false, 404, "not found!");
    } else {
      return new Result<>(item);
    }
  }

  @GetMapping("{blogId}/{albumId}")
  public Result<List<Item>> getItems(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId
  ) {
    List<Item> map = albumService.getAllItems(blogId, albumId);
    if (map == null) {
      return new Result<>(false, 404, "not found!");
    } else {
      return new Result<>(map);
    }
  }

  @PostMapping("{blogId}/{albumId}/{includeByItem}/{idx}")
  public Result<Boolean> post(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @PathVariable("includeByItem") String includeByItem,
      @PathVariable("idx") int idx,
      @RequestBody Item item
  ) {
    boolean flag = albumService.post(blogId, albumId, item, includeByItem, idx);
    return new Result<>(flag, flag ? 200 : 500, flag + "", flag);
  }

  @PutMapping("{blogId}/{albumId}/{includeByItem}/{idx}")
  public Result<Boolean> modifyPost(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @PathVariable("includeByItem") String includeByItem,
      @PathVariable("idx") int idx,
      @RequestBody Item item
  ) {
    boolean flag = albumService.modifyPost(blogId, albumId, item, includeByItem, idx);
    return new Result<>(flag, flag ? 200 : 500, flag + "", flag);
  }

  @PutMapping("{blogId}/{albumId}")
  public Result<Boolean> modifyPost(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @RequestBody Item item
  ) {
    boolean flag = albumService.modifyPost(blogId, albumId, item);
    return new Result<>(flag, flag ? 200 : 500, flag + "", flag);
  }

}
