package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.Comments;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.CommentsRepository;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.CommentService;
import com.example.springbootfinal.service.CustomerOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    ExpertRepository expertRepository;

    @Override
    public void writCommentForExpert(Integer customerOrderId, Integer expertId, String comments, Integer star) {
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() -> new NotFoundException("I cannot find this customerOrder"));
        boolean present = commentsRepository.findByComments(comments).isPresent();
        if (present) {
            throw new DuplicateException("you added descriptions in previous");
        }
        Customer customer = customerOrder.getCustomer();
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("I cannot find this expert"));
        if (customerOrder.getStatusOfOrder().equals(StatusOfOrder.DONE)) {
            Comments comment = new Comments();
            comment.setComments(comments);
            comment.setCustomer(customer);
            expert.setStars(star);
            commentsRepository.save(comment);
            expertRepository.save(expert);
        } else {
            System.out.println("You cannot write comments");
        }
    }
}
