package services;

public interface IServiceRequest {

    void addBookRequest(String nameRequireBook);

    String getCompletedRequests();

    String getNotCompletedRequests();

    void sortRequestsByQuantity();

    void sortRequestsByAlphabet();

    String getRequests();
}
