package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.PhotoAlbum;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PhotoAlbumService extends IService<PhotoAlbum> {

    ResponseResult listAlbum(String name);

    ResponseResult getAlbumById(Integer id);

    ResponseResult insertAlbum(PhotoAlbum photoAlbum);

    ResponseResult updateAlbum(PhotoAlbum photoAlbum);

    ResponseResult deleteAlbumById(Integer id);


    //web端方法开始
    ResponseResult webAlbumList();

    ResponseResult webListPhotos(Integer albumId);

}
