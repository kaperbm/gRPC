import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class HelloWorldClient {
    private final ManagedChannel channel;
    private final HelloWorldServiceGrpc.HelloWorldServiceBlockingStub blockingStub;

    public HelloWorldClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
    }

// Erstellt den eine verbindung zum Server


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

// Beendet die Connection  

    public void sayHello(String name) {
        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setText(name).build();
        Hello.HelloResponse response = blockingStub.hello(request);
        System.out.println("Response from server: " + response.getText());
    }

  // Sendet eine Nachricht an den Server

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
        try {
            String name = "Kacper_Java";
            client.sayHello(name);
        } finally {
            client.shutdown();
        }
    }
}
