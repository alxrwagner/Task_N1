package com.wagner.commentscontrolproj.repository;

import com.wagner.commentscontrolproj.model.Comment;
import com.wagner.commentscontrolproj.projection.CommentProj;
import com.wagner.commentscontrolproj.projection.TopUsersVew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepos extends JpaRepository<Comment, Long> {
    @Query(value = "select u.id as userId,\n" +
            "       u.name as userName,\n" +
            "       count(distinct p.*) as countPosts,\n" +
            "       count(c.*) as countComments,\n" +
            "       (SELECT post.id\n" +
            "        FROM post\n" +
            "        WHERE post.user_id = u.id\n" +
            "        ORDER BY (SELECT COUNT(*)\n" +
            "                  FROM comment\n" +
            "                  WHERE comment.post_id = post.id) DESC, post.id desc\n" +
            "        LIMIT 1) AS idLastPost\n" +
            "from users u\n" +
            "         left join post p on p.user_id = u.id\n" +
            "         left join comment c on c.post_id = p.id\n" +
            "group by u.id, u.name\n" +
            "order by userId desc\n" +
            "limit 10;", nativeQuery = true)
    List<TopUsersVew> getTopUserByMaxComments();

    @Query(value = "SELECT c.id, c.body, c.post_id AS postId FROM comment AS c WHERE c.post_id IN (?1) ", nativeQuery = true)
    List<CommentProj> findByPostIdIn(List<Long> postIds);

}
