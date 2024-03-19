import sys
import grpc
sys.path.append('/home/kacper/Desktop/gRPC/src/main/proto')
import hello_pb2
import hello_pb2_grpc

def say_hello(stub, name):
    request = hello_pb2.HelloRequest(text=name)
    response = stub.hello(request)
    print("Response from server:", response.text)

def main():
    channel = grpc.insecure_channel('localhost:50051')
    stub = hello_pb2_grpc.HelloWorldServiceStub(channel)
    
    try:
        name = "Kacper_python"
        say_hello(stub, name)
    finally:
        channel.close()

if __name__ == '__main__':
    main()
