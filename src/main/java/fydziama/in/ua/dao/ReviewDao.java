package fydziama.in.ua.dao;

import fydziama.in.ua.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ReviewDao extends GeneralDao<Review> {
    List<Review> searchGood(long idGood);

    Page<Review> searchGood(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long idGood);
}
