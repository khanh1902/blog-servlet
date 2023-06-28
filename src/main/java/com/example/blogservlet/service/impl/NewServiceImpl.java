package com.example.blogservlet.service.impl;

import com.example.blogservlet.dao.ICategoryDAO;
import com.example.blogservlet.dao.INewDAO;
import com.example.blogservlet.dto.NewsDTO;
import com.example.blogservlet.model.News;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.service.INewsService;
import com.example.blogservlet.utils.S3Util;

import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class NewServiceImpl implements INewsService {
    @Inject
    private INewDAO newDAO;

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public Page<News> findByCategoryIdWithPaging(News news, Long categoryId, Pageable pageable) {
        news.setListResult(newDAO.findByCategoryIdWithPaging(categoryId, pageable));
        news.setTotalItem(newDAO.getTotalItem());
        double math = (double) news.getTotalItem() / news.getLimit();
        news.setTotalPages((int) Math.ceil(math));
        return new Page<>(news.getListResult(), news.getOffset(), news.getLimit(), news.getTotalItem(), news.getTotalPages(),
                news.getSortName(), news.getAsc(), categoryId);
    }


    @Override
    public List<News> findByCategoryId(Long categoryId) {
        return newDAO.findByCategoryId(categoryId);
    }

    @Override
    public List<News> findAll() {
        List<News> findAllNew = newDAO.findAll();
        return findAllNew;
    }

    @Override
    public News save(NewsDTO newsDTO) {
        try {
            News news = new News();
            news.setTitle(newsDTO.getTitle());
            news.setContent(newsDTO.getContent());
            news.setCreatedBy(newsDTO.getCreatedBy());
            news.setShortDescription(newsDTO.getShortDescription());
            news.setCategoryId(newsDTO.getCategoryId());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            news.setCreatedDate(timestamp);

            S3Util.uploadFile(getFileName(newsDTO.getThumbnail()), newsDTO.getThumbnail().getInputStream());
            String thumbnail = "https://upload-image-shoppingcart.s3.ap-southeast-1.amazonaws.com/" + getFileName(newsDTO.getThumbnail());
            news.setThumbnail(thumbnail);

            Long id = newDAO.save(news);
            return newDAO.findById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;
        return contentDisposition.substring(beginIndex, endIndex);
    }

    @Override
    public News update(NewsDTO newsDTO) {
        try {
            News oldNew = newDAO.findById(newsDTO.getId());

            oldNew.setTitle(newsDTO.getTitle());
            oldNew.setContent(newsDTO.getContent());
            oldNew.setModifiedBy(newsDTO.getModifiedBy());
            oldNew.setShortDescription(newsDTO.getShortDescription());
            oldNew.setCategoryId(newsDTO.getCategoryId());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            oldNew.setModifiedDate(timestamp);
            S3Util.uploadFile(getFileName(newsDTO.getThumbnail()), newsDTO.getThumbnail().getInputStream());
            String thumbnail = "https://upload-image-shoppingcart.s3.ap-southeast-1.amazonaws.com/" + getFileName(newsDTO.getThumbnail());
            oldNew.setThumbnail(thumbnail);
            newDAO.update(oldNew);
            return newDAO.findById(oldNew.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(List<Long> ids) {
        for (Long id : ids) {
            newDAO.deleteById(id);
        }
    }

    @Override
    public Page<News> findAll(News news, Pageable pageable) {
        news.setListResult(newDAO.findAll(pageable));
        news.setTotalItem(newDAO.getTotalItem());
        double math = (double) news.getTotalItem() / news.getLimit();
        news.setTotalPages((int) Math.ceil(math));
        return new Page<>(news.getListResult(), news.getOffset(), news.getLimit(), news.getTotalItem(), news.getTotalPages(),
                news.getSortName(), news.getAsc(), null);
    }

    @Override
    public int getTotalItem() {
        return newDAO.getTotalItem();
    }

    @Override
    public News findById(Long id) {
        return newDAO.findById(id);
    }
}
