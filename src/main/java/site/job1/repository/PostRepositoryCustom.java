package site.job1.repository;

import site.job1.domain.Post;
import site.job1.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
