package util.fileWorker.parse;

import entities.Request;

public class ParseRequest extends Parse {

    private Request[] tempData = new Request[1];

    public ParseRequest(String filePath) {
        super(filePath);
    }

    @Override
    public Request createObject(String str) {
        String[] temp = str.split("/");
        String checkNull = " null";
        if (!temp[0].equals(checkNull)) {
            String id = temp[0].replaceAll("\\s+", "");
            String requireNameBook = temp[1].replaceAll("\\s+", "");
            String requireIsCompleted = temp[2];
            String requireQuantity = temp[3];
            for (int i = 0; i < 1; i++) {
                Request parseRequest = new Request();
                parseRequest.setId(parseLong(id));
                parseRequest.setRequireNameBook(requireNameBook);
                parseRequest.setRequireIsCompleted(parseBoolean(requireIsCompleted));
                parseRequest.setRequireQuantity(parseInteger(requireQuantity));
                tempData[i] = parseRequest;
            }
            return tempData[0];
        }
        return null;
    }
}


