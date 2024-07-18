package data.repositories;

import data.models.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);
    Post deleteById(int id);
    Post findById(int id);
    List<Post> findAll();


}
