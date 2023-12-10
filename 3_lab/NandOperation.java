// Класс для операции NAND
class NandOperation implements BinaryOperation {
    @Override
    public String getOperationName() {
        return "NAND";
    }

    @Override
    public String getOperationSign() {
        return "!&";
    }

    @Override
    public int performOperation(int operand1, int operand2) {
        return ~(operand1 & operand2);
    }
}