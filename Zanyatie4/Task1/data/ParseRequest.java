package Zanyatie4.Task1.data;

import Zanyatie4.Task1.entity.Request;

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
            String requireNameBook = temp[0].replaceAll("\\s+", "");
            String requireIsCompleted = temp[1];
            String requireQuantity = temp[2];
            for (int i = 0; i < 1; i++) {
                tempData[i] = new Request(requireNameBook, parseBoolean(requireIsCompleted), parseInteger(requireQuantity));
            }
            return tempData[0];
        }
        return null;
    }
}


