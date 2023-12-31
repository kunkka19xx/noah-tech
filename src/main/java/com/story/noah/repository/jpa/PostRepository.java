package com.story.noah.repository.jpa;

import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PostProjection;
import com.story.noah.model.Post;
import com.story.noah.payload.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    //    List<PostDto> findAllProjectedBy();
//    @EntityGraph(attributePaths = "content")
    Page<PostProjection> findAllProjectedBy(Pageable pageable);

    Optional<PostProjection> findPostProjectionById(Integer id);

    @Query("SELECT NEW com.story.noah.dto.MiniPostDto(" +
            " p.id as id, p.title as title, p.user.id as userId," +
            " p.tags as tags, p.categories as categories," +
            " u.username as author, p.createdAt as createdAt, " +
            " left(cast(string_agg(pp.content, ' ') as java.lang.String),500) as miniContent, " +
            " (SELECT pop.image FROM PartOfPost pop" +
            " WHERE pop.post.id = p.id" +
            " AND pop.image IS NOT NULL AND pop.image <> ''" +
            " ORDER BY pop.id LIMIT 1) as image" +
            " ) FROM Post p" +
            " JOIN User u ON p.user.id = u.id" +
            " JOIN PartOfPost pp ON p.id = pp.post.id" +
            " WHERE (:#{#filter.userId} IS NULL OR u.id = :#{#filter.userId})" +
            " AND (:#{#filter.title} IS NULL OR :#{#filter.title} = '' OR p.title = :#{#filter.title})" +
            " AND (:#{#filter.tag} IS NULL OR :#{#filter.tag} = '' OR array_contains(p.tags, :#{#filter.tag}) = true )" +
            " AND (:#{#filter.category} IS NULL OR :#{#filter.category} = '' OR array_contains(p.categories, :#{#filter.category}) = true )" +
            " GROUP BY p.id, p.title, p.user.id, u.username, p.createdAt" +
            " ORDER BY p.createdAt DESC")
    Page<MiniPostDto> getMiniPost(Pageable pageable, @Param("filter") Filter filter);


    @Query("SELECT NEW com.story.noah.dto.MiniPostDto(" +
            " p.id as id, p.title as title, p.user.id as userId," +
            " p.tags as tags, p.categories as categories," +
            " u.username as author, p.createdAt as createdAt, " +
            " left(cast(string_agg(pp.content, ' ') as java.lang.String),500) as miniContent, " +
            " (SELECT pop.image FROM PartOfPost pop" +
            " WHERE pop.post.id = p.id" +
            " AND pop.image IS NOT NULL AND pop.image <> ''" +
            " ORDER BY pop.id LIMIT 1) as image" +
            " ) FROM Post p" +
            " JOIN User u ON p.user.id = u.id" +
            " JOIN PartOfPost pp ON p.id = pp.post.id" +
            " WHERE (:#{#filter.userId} IS NULL OR u.id = :#{#filter.userId})" +
            " AND (:#{#filter.title} IS NULL OR :#{#filter.title} = '' OR p.title = :#{#filter.title})" +
            " GROUP BY p.id, p.title, p.user.id, u.username, p.createdAt" +
            " ORDER BY p.createdAt DESC, p.viewNumber limit 5")
    List<MiniPostDto> getLatestPost(@Param("filter") Filter filter);


    @Query("SELECT NEW com.story.noah.dto.MiniPostDto(" +
            " p.id as id, p.title as title, p.user.id as userId," +
            " p.tags as tags, p.categories as categories," +
            " u.username as author, p.createdAt as createdAt, " +
            " left(cast(string_agg(pp.content, ' ') as java.lang.String),500) as miniContent, " +
            " (SELECT pop.image FROM PartOfPost pop" +
            " WHERE pop.post.id = p.id" +
            " AND pop.image IS NOT NULL AND pop.image <> ''" +
            " ORDER BY pop.id LIMIT 1) as image" +
            " ) FROM Post p" +
            " JOIN User u ON p.user.id = u.id" +
            " JOIN PartOfPost pp ON p.id = pp.post.id" +
            " WHERE (:#{#filter.userId} IS NULL OR u.id = :#{#filter.userId})" +
            " AND (:#{#filter.title} IS NULL OR :#{#filter.title} = '' OR p.title = :#{#filter.title})" +
            " GROUP BY p.id, p.title, p.user.id, u.username, p.createdAt" +
            " ORDER BY p.viewNumber, p.createdAt DESC limit 5")
    List<MiniPostDto> getTopPost(@Param("filter") Filter filter);

}


