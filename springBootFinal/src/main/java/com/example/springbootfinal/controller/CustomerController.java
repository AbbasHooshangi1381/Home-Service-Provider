package com.example.springbootfinal.controller;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.card.CardRequestDto;
import com.example.springbootfinal.dto.comments.CommentsRequestDto;
import com.example.springbootfinal.dto.customer.CriteriaSearchDtoOfCustomer;
import com.example.springbootfinal.dto.customerOrder.CustomerOrderDTO;
import com.example.springbootfinal.dto.customerOrder.CustomerOrderResponseDto;
import com.example.springbootfinal.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    SuggestionService suggestionService;
    @Autowired
    WalletService walletService;
    @Autowired
    RegistrationServices registrationService;

/*    @PostMapping("/register-user")
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody BaseSaveDto adminSaveDto) {
          registrationService.saveCustomer(adminSaveDto.getFirstName(), adminSaveDto.getLastName(),
                adminSaveDto.getEmail(), adminSaveDto.getUserName(), adminSaveDto.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("ok!");
    }*/
    @GetMapping("/logins/{username}/{password}")
    public ResponseEntity<BaseResponseDto> checkCustomer( @PathVariable String username, @PathVariable String password) {
        Customer customer = customerService.findByUserNameAndPassword(username, password).get();

        BaseResponseDto baseResponseDto = modelMapper.map(customer, BaseResponseDto.class);
        return new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
    }
    @PutMapping("/changePassword/{oldPassword}/{password}")
    public ResponseEntity<String> changePassword( @PathVariable String oldPassword, @PathVariable String password) {
        customerService.changePassword(oldPassword, password);
        return ResponseEntity.ok("pasword changed !");
    }
    @GetMapping("/findAllCustomertByCriteria")
    public List<CriteriaSearchDtoOfCustomer> findAllCustomerByCriteria(@Valid @RequestBody Map<String, String> param) {
        List<CriteriaSearchDtoOfCustomer> criteriaSearchDtoOfCustomerList = new ArrayList<>();
        List<Customer> allSpecialistsByCriteria = customerService.findAllCustomersByCriteria(param);
        for (Customer s : allSpecialistsByCriteria) {
            CriteriaSearchDtoOfCustomer map = modelMapper.map(s, CriteriaSearchDtoOfCustomer.class);
            criteriaSearchDtoOfCustomerList.add(map);
        }
        return criteriaSearchDtoOfCustomerList;
    }
    //////////////////////////////////////////////////////////////////
    @PostMapping("/register-comments")
    public ResponseEntity<Integer> saveComments(@Valid @RequestBody CommentsRequestDto commentsRequestDto) {
        commentService.writCommentForExpert(commentsRequestDto.getCustomerOrderId(), commentsRequestDto.getExpertId(),
                commentsRequestDto.getComments(), commentsRequestDto.getStar());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsRequestDto.getStar());
    }
    @PostMapping("/saveOrder")
    public ResponseEntity<CustomerOrderResponseDto> saveOrder(@Valid @RequestBody CustomerOrderDTO customerOrderDto) throws Exception {
        String descriptionOfOrder = customerOrderDto.getDescriptionOfOrder();
        Double proposedPrice = customerOrderDto.getProposedPrice();
        String timeOfWork = customerOrderDto.getTimeOfWork();
        String address = customerOrderDto.getAddress();
        StatusOfOrder statusOfOrder = customerOrderDto.getStatusOfOrder();
        Integer customerId = customerOrderDto.getCustomerId();
        Integer subDutyId = customerOrderDto.getSubDutyId();
        CustomerOrder customerOrder = customerOrderService.saveOrder(descriptionOfOrder, proposedPrice, timeOfWork, address, statusOfOrder, customerId, subDutyId);
        CustomerOrderResponseDto responseDto = modelMapper.map(customerOrder,CustomerOrderResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/changeStatusOfOrderByCustomerToWaitingToCome/{orderId}")
    public ResponseEntity<String> changeStatusOfOrderByCustomerToWaitingToCome( @PathVariable Integer orderId) {
        customerOrderService.changeStatusOfOrderByCustomerToWaitingToCome(orderId);
        return ResponseEntity.ok("Order status changed to WAITING_FOR_COMING_EXPERT");
    }

    @GetMapping("/showSuggestionByPrice/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerIdOrderByProposedPriceDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerIdOrderByProposedPriceDesc = suggestionService.showSuggestionOrderByPriceOfSuggestions(customerOrderId);
        return ResponseEntity.ok(byCustomerIdOrderByProposedPriceDesc);
    }
    @GetMapping("/showSuggestionsByExpertStar/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerOrderIdOrderByExpertStarsDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerOrderIdOrderByExpertStarsDesc = suggestionService.showSuggestionOrderByExpertStars(customerOrderId);
        return ResponseEntity.ok(byCustomerOrderIdOrderByExpertStarsDesc);
    }

    @PutMapping("/payByCreditOfAccount/{customerOrderId}/{expertId}")
    public ResponseEntity<String> payByCreditOfAccount( @PathVariable Integer customerOrderId, @PathVariable Integer expertId) {
        walletService.payByCreditOfAccount(customerOrderId,expertId);
        return ResponseEntity.ok("send");
    }
    @PutMapping("/payByCard")
    @CrossOrigin
    public ResponseEntity<String> payByCard( @RequestBody CardRequestDto cardRequestDto) {
        walletService.payByCard(952,855);
        return ResponseEntity.ok("paid!");
    }
}

