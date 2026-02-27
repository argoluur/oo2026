package ee.argo.veebipood.service;


import ee.argo.veebipood.dto.OrderRowDto;
import ee.argo.veebipood.entity.Orders;
import ee.argo.veebipood.entity.OrdersRow;
import ee.argo.veebipood.entity.Person;
import ee.argo.veebipood.entity.Product;
import ee.argo.veebipood.repository.OrderRepository;
import ee.argo.veebipood.repository.PersonRepository;
import ee.argo.veebipood.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    //@Autowired --> Dependency Injection
    //@RequiredArgsConstructor --> Dependency Injection

    //Tagataustal tõmmatakse sisse tema mälukohaga

    private OrderRepository orderRepository;
    private PersonRepository personRepository;
    private ProductRepository productRepository;

    public Orders saveOrder(Long personId, String parcelMachine, List<OrderRowDto> ordersRows){
        Orders orders = new Orders();
        orders.setCreated(new Date());
        orders.setParcelMachine(parcelMachine);
        //orders.setOrdersRows(ordersRows);
        Person person = personRepository.findById(personId).orElseThrow();
        orders.setPerson(person);
        orders.setTotal(calculateOrdersTotal(ordersRows, orders));
        return orderRepository.save(orders);
    }

    private double calculateOrdersTotal(List<OrderRowDto> ordersRows, Orders orders) {
        double total = 0;
        List<OrdersRow> ordersRowsInOrder = new ArrayList<>();
        for (OrderRowDto ordersRow : ordersRows) {
            Product product = productRepository.findById(ordersRow.productId()).orElseThrow();
            total += product.getPrice() * ordersRow.quantity();

            OrdersRow orderRowInOrder = new OrdersRow();
            orderRowInOrder.setProduct(product);
            orderRowInOrder.setQuantity(ordersRow.quantity());
            ordersRowsInOrder.add(orderRowInOrder);

        }
        orders.setOrdersRows(ordersRowsInOrder);
        return total;
    }
}
