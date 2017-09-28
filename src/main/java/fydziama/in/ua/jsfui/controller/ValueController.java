package fydziama.in.ua.jsfui.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class ValueController {

    @Autowired
    private UserController userController;
    @Autowired
    private GoodController goodController;
    @Autowired
    private BrandController brandController;
    @Autowired
    private MessageController messageController;
    @Autowired
    private ReviewController reviewController;
    @Autowired
    private OrderController orderController;
    @Autowired
    private OrderDetailController orderDetailController;

//    private User selectedUser=new User();
//    private Good selectedGood=new Good();
//    private Brand selectedBrand=new Brand();
//    private Message selectedMessage=new Message();
//    private Review selectedReview=new Review();
//    private Order selectedOrder=new Order();
//    private OrderDetail selectedOrderDetail=new OrderDetail();
}
