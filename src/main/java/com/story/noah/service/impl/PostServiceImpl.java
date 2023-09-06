package com.story.noah.service.impl;

import com.story.noah.common.DateTimeUtil;
import com.story.noah.dto.MiniPostDto;
import com.story.noah.dto.PartCreationDto;
import com.story.noah.dto.PostCreationDto;
import com.story.noah.dto.PostWithUserIdProjection;
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
    public Page<PostWithUserIdProjection> getAll(Pageable pageable) {
        return postRepository.findAllProjectedBy(pageable);
    }

    @Override
    public Page<MiniPostDto> getMiniPost(Pageable pageable, Filter filter) {
        return postRepository.getMiniPost(pageable, filter);
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
        for (PartCreationDto part : parts
        ) {
            PartOfPost partOfPost = new PartOfPost();
            BeanUtils.copyProperties(part, partOfPost);
            partOfPost.setIndex(i++);
            MultipartFile file = part.getFile();
            if (file.isEmpty() || file == null) {
                partEntities.add(partOfPost);
            } else {
                try (InputStream is = file.getInputStream()) {
                    String fileName = file.getOriginalFilename();
                    String newFileName = fileName.replace(".", DateTimeUtil.getNowForFileName().concat("."));
                    System.out.println(newFileName);
                    Path target = Paths.get(destination).resolve(Paths.get(newFileName)).normalize();
                    System.out.println(target.toString());
                    Files.copy(is, target);
                    String time = LocalDateTime.now().toString();
                    part.setImage(target.toString());
                    partOfPost.setImage(target.toString());

                    partOfPost.setPost(postEntity);
                    partEntities.add(partOfPost);
                } catch (IOException io) {
                    io.printStackTrace();
                    return null;
                }
            }

        }
        postEntity.setContent(partEntities);
        postRepository.save(postEntity);
        partRepository.saveAll(partEntities);
        return postEntity;
    }
}
