package org.magnum.mobilecloud.video.controller;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import retrofit.http.Query;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RepositoryRestController
public class VideoController {
    @Autowired
    VideoRepository videoRepository;

    @RequestMapping(value="/video/{id}/like", method = RequestMethod.POST)
    public void likeVideo(@PathVariable Long id, HttpServletResponse resp) {
        Optional<Video> results = this.videoRepository.findById(id);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "video not found");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Video video = results.get();
        if (video.getLikedBy().contains(username)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "already done like");
        }
        Set<String> likeBy = video.getLikedBy();
        Long likes = video.getLikes();
        likes++;
        likeBy.add(username);
        video.setLikes(likes);
        video.setLikedBy(likeBy);
        this.videoRepository.save(video);
    }

    @RequestMapping(value ="/video/{id}/unlike", method = RequestMethod.POST)
    public void unlikeVideo(@PathVariable Long id, HttpServletResponse resp) {
        Optional<Video> results = this.videoRepository.findById(id);
        if (results.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "video not found");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Video video = results.get();
        if (!video.getLikedBy().contains(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "didn't like yet");
        }
        Set<String> likeBy = video.getLikedBy();
        Long likes = video.getLikes();
        likes--;
        likeBy.add(username);
        video.setLikes(likes);
        video.setLikedBy(likeBy);
        this.videoRepository.save(video);
    }

    @RequestMapping(value ="/video", method = RequestMethod.POST)
    public @ResponseBody Video addVideo(@RequestBody Video video) {
        Video result = this.videoRepository.save(video);
        return result;
    }

    @RequestMapping(value="/video/{id}", method=RequestMethod.GET)
    public @ResponseBody Video getVideo(@PathVariable Long id, HttpServletResponse resp) {
        Optional<Video> result = this.videoRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "video not found");
        } else {
            return result.get();
        }
    }

    @RequestMapping(value ="/video", method = RequestMethod.GET)
    public @ResponseBody List<Video> videos() {
        List<Video> vidoes = (List<Video>) this.videoRepository.findAll();
        return vidoes;
    }

    @RequestMapping(value = "/video/search/findByDurationLessThan", method=RequestMethod.GET)
    public @ResponseBody List<Video> findByDurationLessThan(
            @Query("duration") Long duration,
            HttpServletResponse resp
    ) {
        List<Video> vidoes = this.videoRepository.findByDurationLessThan(duration);
        return vidoes;
    }

    @RequestMapping(value = "/video/search/findByName", method = RequestMethod.GET)
    public @ResponseBody List<Video> findByName(
            @Query("title") String title,
            HttpServletResponse resp
    ) {
        List<Video> vidoes = this.videoRepository.findByName(title);
        return vidoes;
    }
}

