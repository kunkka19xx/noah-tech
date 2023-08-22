package com.story.noah.repository.jpa;

import com.story.noah.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    @Query("Select l.id from Like l where l.user.id = :userId AND l.postId = :postId")
    Integer getLikeId(Integer userId, Integer postId);


    @Modifying
    @Query("Update Like l set l.total = (l.total + :value) where l.postId = :postId")
    void updateTotalPostLike(int value, Integer postId);

    @Query("Select distinct l.total from Like l where l.postId = :postId")
    Integer findDistinctTotalByPostId(int postId);
}
