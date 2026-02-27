package ee.argo.veebipood.controller;

import ee.argo.veebipood.dto.OrderRowDto;
import ee.argo.veebipood.entity.Orders;
import ee.argo.veebipood.entity.OrdersRow;
import ee.argo.veebipood.repository.OrderRepository;
import ee.argo.veebipood.service.OrderService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class OrderController {

    private OrderRepository orderRepository; //dependency injection, ei võta ressurssi
    private OrderService orderService;

    @GetMapping("orders")
    public List<Orders> getOrders(){
        return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Orders> deleteOrders(@PathVariable Long id){
        orderRepository.deleteById(id);  //kustutab
        return orderRepository.findAll();  //uuendatud seis
    }

    @PostMapping("orders")
    public Orders addOrders(@RequestParam Long personId,
                            @RequestParam(required = false) String parcelMachine,
                            @RequestBody List<OrderRowDto> ordersRows){
        return orderService.saveOrder(personId, parcelMachine, ordersRows);

    }
}
