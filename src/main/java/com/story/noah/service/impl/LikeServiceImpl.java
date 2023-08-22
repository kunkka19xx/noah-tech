package com.story.noah.service.impl;

import com.story.noah.constants.LikeConstants;
import com.story.noah.model.Like;
import com.story.noah.model.User;
import com.story.noah.payload.LikeInput;
import com.story.noah.repository.jpa.LikeRepository;
import com.story.noah.service.LikeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Override
    @Transactional
    public Like processLikePost(LikeInput input) {
        // check exist like;
        int postId = input.getPostId();
        Integer likeId = likeRepository.getLikeId(input.getUserId(), postId);
        Like like = new Like();
        BeanUtils.copyProperties(input, like);
        User user = new User();
        user.setId(input.getUserId());
        like.setUser(user);
        if (likeId == null) {
            like.setType(LikeConstants.POST);
            like.setCreatedAt(LocalDateTime.now());
            Integer total = likeRepository.findDistinctTotalByPostId(postId);
            like.setTotal(Objects.requireNonNullElse(total, 1));
            likeRepository.save(like);
            //increase like total
            likeRepository.updateTotalPostLike(1, postId);
        } else {
            likeRepository.deleteById(likeId);
            //decrease total like
            likeRepository.updateTotalPostLike(-1, postId);
        }
        return like;
    }
}
