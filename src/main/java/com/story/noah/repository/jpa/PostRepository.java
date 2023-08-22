package com.story.noah.repository.jpa;

import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PostWithUserIdProjection;
import com.story.noah.model.Post;
import com.story.noah.payload.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    //    List<PostDto> findAllProjectedBy();
//    @EntityGraph(attributePaths = "content")
    Page<PostWithUserIdProjection> findAllProjectedBy(Pageable pageable);

    @Query("SELECT NEW com.story.noah.dto.MiniPostDto(" +
            " p.id as id, p.title as title, p.user.id as userId," +
            " u.userName as author, p.createdAt as createdAt, " +
            " left(cast(string_agg(pp.content, ' ') as java.lang.String),60) as content, " +
            " (SELECT pop.image FROM PartOfPost pop" +
            " WHERE pop.post.id = p.id" +
            " AND pop.image IS NOT NULL AND pop.image <> ''" +
            " ORDER BY pop.id LIMIT 1) as image" +
            " ) FROM Post p" +
            " JOIN User u ON p.user.id = u.id" +
            " JOIN PartOfPost pp ON p.id = pp.post.id" +
            " WHERE (:#{#filter.userId} IS NULL OR u.id = :#{#filter.userId})" +
            " AND (:#{#filter.title} IS NULL OR :#{#filter.title} = '' OR p.title = :#{#filter.title})" +
            " GROUP BY p.id, p.title, p.user.id, u.userName, p.createdAt")
    Page<MiniPostDto> getMiniPost(Pageable pageable,@Param("filter") Filter filter);

}


