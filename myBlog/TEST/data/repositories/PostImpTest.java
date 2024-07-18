package data.repositories;

import data.models.Comment;
import data.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostImpTest {
    private Post post;


    @BeforeEach
    void setUp() {
         post = new Post();

    }
    @Test
    public void testThatPostRepoCanSave_OneComment(){
        Comment comment = new Comment();
        comment.setContent("i love semicolon");
        comment.setCreatedAt(LocalDateTime.now());
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        post.setComments(comments);
        assertEquals(1,post.getComments().size());
        assertEquals(comments,post.getComments());
        assertEquals("i love semicolon", post.getComments().get(0).getContent());
    }

    @Test
    public void testThatPostRepoCanSave_MultipleComments(){
        Comment comment = new Comment();
        comment.setContent("i love semicolon");
        comment.setCreatedAt(LocalDateTime.now());

        Comment comment1 = new Comment();
        comment1.setContent("i love G-Strings too");
        comment1.setCreatedAt(LocalDateTime.now());

        Comment comment2 = new Comment();
        comment2.setContent("removing Gstrings is the best feeling");
        comment2.setCreatedAt(LocalDateTime.now());

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment1);
        comments.add(comment2);
        post.setComments(comments);

        assertEquals(3,post.getComments().size());
        assertEquals(comments,post.getComments());
        assertEquals("i love semicolon", post.getComments().get(0).getContent());
        assertEquals("i love G-Strings too", post.getComments().get(1).getContent());
        assertEquals("removing Gstrings is the best feeling", post.getComments().get(2).getContent());
    }

    @Test
    public void testThatPostRepoCanDelete_OneComment(){

    Comment comment = new Comment();
    comment.setContent("i love semicolon");
    comment.setCreatedAt(LocalDateTime.now());
    List<Comment> comments = new ArrayList<>();
    comments.add(comment);
    post.setComments(comments);
    assertEquals(1,post.getComments().size());
    comments.remove(comment);
    assertEquals(0,post.getComments().size());
    }

}