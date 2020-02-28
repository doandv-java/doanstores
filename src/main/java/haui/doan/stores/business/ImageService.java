package haui.doan.stores.business;

import haui.doan.stores.domain.Image;
import haui.doan.stores.dto.request.ImageRequest;
import haui.doan.stores.framework.CommonConstant;
import haui.doan.stores.framework.Settings;
import haui.doan.stores.persistents.ImageRepository;
import haui.doan.stores.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Path;

@Service
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;

    private final Settings settings;

    @Autowired
    public ImageService(ImageRepository imageRepository, Settings settings) {
        this.imageRepository = imageRepository;
        this.settings = settings;
    }

    //Save Image
    public Image saveImage(ImageRequest request) {
        Image image;
        boolean checkImage = FileUtils.checkFileNullOrEmpty(request.getImage());
        if (StringUtils.isEmpty(request.getId())) {
            if (!checkImage) {
                image = new Image();
                String name = FileUtils.store(request.getImage(), Path.of(settings.getImageRoot().getPath()));
                String url = new StringBuilder("/").append(settings.getImageRoot().getPath()).append("/").append(name).toString();
                image.setId(request.getId());
                image.setName(name);
                image.setUrl(url);
                return imageRepository.save(image);
            } else {
                return getDefaultImage();
            }
        } else {
            if (!checkImage) {
                image = imageRepository.getOne(request.getId());
                FileUtils.deletePath(image.getUrl());
                String name = FileUtils.store(request.getImage(), Path.of(settings.getImageRoot().getPath()));
                String url = new StringBuilder("/").append(settings.getImageRoot().getPath()).append("/").append(name).toString();
                image.setId(request.getId());
                image.setName(name);
                image.setUrl(url);
                return imageRepository.save(image);
            } else {
                return imageRepository.getOne(request.getId());
            }
        }
    }

    public Image getDefaultImage() {
        Image image = imageRepository.findImageByNameIs(CommonConstant.DEFAULT_IMAGE.NAME);
        if (image == null) {
            image = new Image();
            image.setName(CommonConstant.DEFAULT_IMAGE.NAME);
            image.setUrl(CommonConstant.DEFAULT_IMAGE.URL);
            return imageRepository.save(image);
        }
        return image;
    }
}
