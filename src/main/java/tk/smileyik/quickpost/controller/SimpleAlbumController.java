package tk.smileyik.quickpost.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.smileyik.quickpost.entity.SimpleAlbum;
import tk.smileyik.quickpost.service.ISimpleAlbumService;
import tk.smileyik.quickpost.util.Result;

import java.util.List;
import java.util.Map;

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
    return new Result<>(
        simpleAlbumService.getAlbumMarkdown(blogId, albumId),
        simpleAlbumService.getSimpleAlbumById(blogId, albumId)
    );
  }

  @DeleteMapping("{blogId}/{albumId}")
  public Result<Boolean> deleteAlbum(
      @PathVariable("blogId") String blogId,
      @PathVariable("albumId") String albumId
  ) {
    return new Result<>(simpleAlbumService.deleteSimpleAlbum(blogId, albumId));
  }

  @PostMapping("{blogId}")
  public Result<Boolean> addAlbum(
      @PathVariable("blogId") String blogId,
      @RequestBody Map<String, Object> body
  ) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return new Result<>(simpleAlbumService.addSimpleAlbum(
        blogId,
        objectMapper.readValue(objectMapper.writeValueAsString(body.get("album")), SimpleAlbum.class),
        body.get("markdown").toString()
    ));
  }

  @PutMapping("{blogId}")
  public Result<Boolean> updateAlbum(
      @PathVariable("blogId") String blogId,
      @RequestBody Map<String, Object> body
  ) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return new Result<>(simpleAlbumService.updateSimpleAlbum(
        blogId,
        objectMapper.readValue(objectMapper.writeValueAsString(body.get("album")), SimpleAlbum.class),
        body.get("markdown").toString()
    ));
  }
}
