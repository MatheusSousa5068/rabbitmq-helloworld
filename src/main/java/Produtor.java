import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Produtor {

    public static void main(String[] args) throws Exception {
        ConnectionFactory fabricaConexao = new ConnectionFactory();

        fabricaConexao.setHost("localhost");
        fabricaConexao.setPort(5672);
        fabricaConexao.setUsername("mqadmin");
        fabricaConexao.setPassword("Admin123XX_");

        String nomeFila = "FILADOTREMVRUMVRUM";

        try (
            Connection conexao = fabricaConexao.newConnection();
            Channel canal = conexao.createChannel()
        ) {
            canal.queueDeclare(nomeFila, false, false, false, null);
            String conteudoMensagem = "Matheus passou por aqui!";
            canal.basicPublish("", nomeFila, null, conteudoMensagem.getBytes());
            System.out.println("Mensagem enviada: " + conteudoMensagem);
        }
    }
}
