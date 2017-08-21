package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.ReviewDao;
import fydziama.in.ua.entity.Review;
import fydziama.in.ua.spring.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService implements ReviewDao {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getAll(Sort sort) {
        return reviewRepository.findAll();
    }

    @Override
    public Page<Review> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return reviewRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<Review> search(String... searchString) {
        return reviewRepository.findByReviewContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByUserName(searchString[0], searchString[1]);
    }

    @Override
    public Page<Review> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return reviewRepository.findByReviewContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByUserName(searchString[0], searchString[1], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Review get(long id) {
        return reviewRepository.findOne(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}

