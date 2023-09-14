package com.story.noah.service.impl;

import com.story.noah.common.DateTimeUtil;
import com.story.noah.constants.InfoConstant;
import com.story.noah.constants.PatternConstant;
import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PartCreationDto;
import com.story.noah.dto.PostCreationDto;
import com.story.noah.dto.PostProjection;
import com.story.noah.model.PartOfPost;
import com.story.noah.model.Post;
import com.story.noah.model.User;
import com.story.noah.payload.Filter;
import com.story.noah.repository.jpa.PartOfPostRepository;
import com.story.noah.repository.jpa.PostRepository;
import com.story.noah.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:config.properties")
public class PostServiceImpl implements PostService {

    @Value("${noah.tech.file.image.path}")
    String destination;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PartOfPostRepository partRepository;

    @Override
    public boolean createPost(Post post) {
        List<PartOfPost> part = post.getContent();

        postRepository.save(post);
        partRepository.saveAll(part);
        return true;
    }

    @Override
    public Page<PostProjection> getAll(Pageable pageable) {
        return postRepository.findAllProjectedBy(pageable);
    }

    @Override
    public Optional<PostProjection> findPostProjectionById(Integer id) {
        return postRepository.findPostProjectionById(id);
    }

    @Override
    public Page<MiniPostDto> getMiniPost(Pageable pageable, Filter filter) {
        return postRepository.getMiniPost(pageable, filter);
    }

    @Override
    public List<MiniPostDto> getLatestPost(Filter filter) {
        return postRepository.getLatestPost(filter);
    }

    @Override
    @Transactional
    public Post makePost(PostCreationDto post) {
        List<PartCreationDto> parts = post.getContent();
        Post postEntity = new Post();
        List<PartOfPost> partEntities = new ArrayList<>();
        BeanUtils.copyProperties(post, postEntity);
        User user = new User();
        user.setId(post.getUserId());
        postEntity.setUser(user);
        short i = 0;
        LocalDateTime now = LocalDateTime.now();
        int totalWords = 0;
        for (PartCreationDto part : parts
        ) {
            PartOfPost partOfPost = new PartOfPost();
            BeanUtils.copyProperties(part, partOfPost);
            partOfPost.setIndex(i++);
            totalWords += partOfPost.getContent().split(PatternConstant.SPACE).length;
            MultipartFile file = part.getFile();
            if (file.isEmpty()) {
                partEntities.add(partOfPost);
            } else {
                try (InputStream is = file.getInputStream()) {
                    String fileName = file.getOriginalFilename();
                    String newFileName = fileName.replace(PatternConstant.DOT,
                            DateTimeUtil.getNowForFileName().concat(PatternConstant.DOT));
                    Path target = Paths.get(destination).resolve(Paths.get(newFileName)).normalize();
                    Files.copy(is, target);
                    part.setImage(target.toString());
                    partOfPost.setImage(target.toString());
                    partOfPost.setPost(postEntity);
                    partOfPost.setCreatedAt(now);
                    partOfPost.setUpdatedAt(now);
                    partEntities.add(partOfPost);
                } catch (IOException io) {
                    io.printStackTrace();
                    return null;
                }
            }
        }
        postEntity.setContent(partEntities);
        postEntity.setLength((totalWords / InfoConstant.AVERAGE_READING_SPEED_MIN) + 1);
        postEntity.setCreatedAt(now);
        postEntity.setUpdatedAt(now);
        postRepository.save(postEntity);
        partRepository.saveAll(partEntities);
        return postEntity;
    }

}
