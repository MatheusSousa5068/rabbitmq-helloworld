import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receptor {
    public static void main(String[] args) throws Exception {
        System.out.println("Consumidor");

        String fila = "FILADOTREMVRUMVRUM";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");

        Connection conexao = connectionFactory.newConnection();


        Channel canal = conexao.createChannel();
        canal.queueDeclare(fila, false, false, false, null);

        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            System.out.println("Mensagem: " + mensagem);
        };

        canal.basicConsume(fila, true, callback, consumerTag -> {});

    }
}