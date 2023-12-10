// Класс для операции XOR
class XorOperation implements BinaryOperation {
    @Override
    public String getOperationName() {
        return "XOR";
    }

    @Override
    public String getOperationSign() {
        return "^";
    }

    @Override
    public int performOperation(int operand1, int operand2) {
        return operand1 ^ operand2;
    }
}