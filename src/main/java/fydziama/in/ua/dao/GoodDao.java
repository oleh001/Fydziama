package fydziama.in.ua.dao;

import fydziama.in.ua.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GoodDao extends GeneralDao<Good> {
    // постраничный вывод книг определенного жанра
    Page<Good> findByBrand(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId);

        List<Good> findByNewsEquals();

        List<Good> findBySpecialsEquals();
//    List<Good> findNewDish();

    // обновить данные рейтинга
    void updateRating(long totalRating, long totalVoteCount, int avgRating, long id);
}
