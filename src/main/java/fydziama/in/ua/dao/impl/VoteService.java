package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.VoteDao;
import fydziama.in.ua.entity.Vote;
import fydziama.in.ua.spring.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VoteService implements VoteDao {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public boolean isVisibility(List<Vote> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<Vote> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @Override
    public List<Vote> getAll(Sort sort) {
        return voteRepository.findAll();
    }

    @Override
    public Page<Vote> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return voteRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<Vote> search(String... searchString) {
        return voteRepository.findByGoodNameContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByGoodName(searchString[0], searchString[1]);
    }

    @Override
    public Page<Vote> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return voteRepository.findByGoodNameContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByGoodName(searchString[0], searchString[1], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Vote get(long id) {
        return voteRepository.findOne(id);
    }

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public void delete(Vote vote) {
        voteRepository.delete(vote);
    }
}
