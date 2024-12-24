package org.magnum.mobilecloud.video.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RepositoryRestResource(path = "video")
public interface VideoRepository extends CrudRepository<Video, Long> {
    public @ResponseBody Video findById(long id);

    public @ResponseBody List<Video> findByName(String title);

    public @ResponseBody List<Video> findByDurationLessThan(long duration);

    public @ResponseBody Video save(Video video);
}
