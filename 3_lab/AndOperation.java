// Класс для операции AND
class AndOperation implements BinaryOperation {
    @Override
    public String getOperationName() {
        return "AND";
    }

    @Override
    public String getOperationSign() {
        return "&";
    }

    @Override
    public int performOperation(int operand1, int operand2) {
        return operand1 & operand2;
    }
}