package E2ETesting;

import java.util.List;

public class CreateOrderRequestBody
{
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
