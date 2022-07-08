package tk.smileyik.quickpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.smileyik.quickpost.entity.Item;
import tk.smileyik.quickpost.service.IAlbumService;
import tk.smileyik.quickpost.service.INewestPostService;
import tk.smileyik.quickpost.util.Result;

import java.util.List;

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
  private INewestPostService newestPostService;

  @Autowired
  public void setAlbumService(IAlbumService albumService) {
    this.albumService = albumService;
  }

  @Autowired
  public void setNewestPostService(INewestPostService newestPostService) {
    this.newestPostService = newestPostService;
  }

  /**
   * 获取某一篇文章的详细信息， 其中prev被所有正文所替代。
   * @param blogId
   * @param albumId
   * @param itemId
   * @return
   */
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

  /**
   * 获取某一文章集下的所有文章信息，prev为正常简要信息。
   * @param blogId
   * @param albumId
   * @return
   */
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

  /**
   * 发表一篇新的文章。
   * @param blogId
   * @param albumId
   * @param includeByItem
   * @param idx
   * @param item
   * @return
   */
  @PostMapping("{blogId}/{albumId}/{includeByItem}/{idx}")
  public Result<Boolean> post(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @PathVariable("includeByItem") String includeByItem,
      @PathVariable("idx") int idx,
      @RequestBody Item item
  ) {
    boolean flag = albumService.post(blogId, albumId, item, includeByItem, idx) &&
                   newestPostService.updateNewestPost(blogId, albumId, item);
    return new Result<>(flag, flag ? 200 : 500, flag + "", flag);
  }

  /**
   * 修改一篇文章。
   * @param blogId
   * @param albumId
   * @param includeByItem
   * @param idx
   * @param item
   * @return
   */
  @PutMapping("{blogId}/{albumId}/{includeByItem}/{idx}")
  public Result<Boolean> modifyPost(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @PathVariable("includeByItem") String includeByItem,
      @PathVariable("idx") int idx,
      @RequestBody Item item
  ) {
    boolean flag = albumService.modifyPost(blogId, albumId, item, includeByItem, idx) &&
                   newestPostService.updateNewestPost(blogId, albumId, item);
    return new Result<>(flag, flag ? 200 : 500, flag + "", flag);
  }

  /**
   * 修改一篇文章但是不修改这篇文章所在的位置。
   * @param blogId
   * @param albumId
   * @param item
   * @return
   */
  @PutMapping("{blogId}/{albumId}")
  public Result<Boolean> modifyPost(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @RequestBody Item item
  ) {
    boolean flag = albumService.modifyPost(blogId, albumId, item) &&
                   newestPostService.updateNewestPost(blogId, albumId, item);
    return new Result<>(flag, flag ? 200 : 500, flag + "", flag);
  }

  @DeleteMapping("{blogId}/{albumId}/{itemId}")
  public Result<Item> deletePost(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId,
      @PathVariable("itemId") String itemId
  ) {
    Item item = albumService.deletePost(blogId, albumId, itemId);
    if (item == null) {
      return new Result<>(false, 500, "delete failed!", null);
    }
    return new Result<>(item);
  }



}
