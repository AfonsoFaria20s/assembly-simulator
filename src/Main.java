public class Main {
    public static void main(String[] args) {
        int[] program = {
                0x0100000A,  // MOV R0, 10
                0x01010005,  // MOV R1, 5
                0x01030006,  // MOV R3, 6
                0x02020001,  // ADD R2, R0, R1
                0x03070003   // SUB R0, R5, R7
        };

        Assembly asm = new Assembly();
        asm.loadProgram(program);
        asm.execute();
    }
}
