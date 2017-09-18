package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.MessageDao;
import fydziama.in.ua.entity.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class MessageController  extends AbstractController<Message>{

    @Autowired
    private MessageDao messageDao;

    private Message selectedMessage=new Message();

    private String errorMessage="";

    private String visiblePaginator;

    public void save() {
        messageDao.save(selectedMessage);
        selectedMessage=new Message();
    }

    @Override
    public String vizibilityAction() {
        return "true";
    }

    @Override
    public Page<Message> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        visiblePaginator="visible" + vizibilityAction();
        return null;
    }

    @Override
    public void addAction() {
        selectedMessage = new Message();
    }

    @Override
    public void editAction() {
    }

    @Override
    public void deleteAction() {
        messageDao.delete(selectedMessage);
    }
}
