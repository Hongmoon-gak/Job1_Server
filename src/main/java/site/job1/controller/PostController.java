package site.job1.controller;

import site.job1.config.data.UserSession;
import site.job1.request.PostCreate;
import site.job1.request.PostEdit;
import site.job1.request.PostSearch;
import site.job1.response.PostResponse;
import site.job1.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/job1")
    public Long job1(UserSession userSession) {
        log.info(">>>{}", userSession.id);
        return userSession.id;
    }

    @GetMapping("/project")
    public String project(UserSession userSession) {
        return "인증이 필요한 페이지";
    }

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        request.validate();
        postService.write(request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}












