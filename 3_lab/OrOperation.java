// Класс для операции OR
class OrOperation implements BinaryOperation {
    @Override
    public String getOperationName() {
        return "OR";
    }

    @Override
    public String getOperationSign() {
        return "|";
    }

    @Override
    public int performOperation(int operand1, int operand2) {
        return operand1 | operand2;
    }
}