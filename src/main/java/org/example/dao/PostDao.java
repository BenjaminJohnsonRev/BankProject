package org.example.dao;

import org.example.entity.Account;
import org.example.entity.Post;

import java.util.List;

public interface PostDao {
    public void insert(Post post);
    public Post getPostById(int id);
    public Post getPostByAccountid2(int accountid2);
    public List<Post> getAllPostsForAccount(int accountid2);
    public List<Post> getAllPosts();
    public void delete(int id);

}
