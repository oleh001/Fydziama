package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.MessageDao;
import fydziama.in.ua.entity.Message;
import fydziama.in.ua.spring.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService implements MessageDao {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public boolean isVisibility(List<Message> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<Message> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> search(String... searchString) {
        return null;
    }

    @Override
    public Message get(long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public List<Message> getAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Message> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public Page<Message> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
