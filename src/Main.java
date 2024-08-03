public class Main {
    public static void main(String[] args) {
        /*
            Example program:
            pc |    instruction   |           explanation            |
            ---|------------------|----------------------------------|
            1  | MOV R1, 10       | Set register 1 to 10             |
            2  | MOV R2, 20       | Set register 2 to 20             |
            3  | JMP_LT R1, R2, 5 | If R1 < R2 jump to instruction 5 |
            4  | MOV R3, 4        | Set register 3 to 4              |
            5  |MOV R3, 7         | Set register 3 to 7              |
         */

        int[] program = {
                0x0101000A, // MOV R1, 10
                0x01020014, // MOV R2, 20
                0x10010205, // JMP_LT R1, R2, 5
                0x01030004, // MOV R3, 4
                0x01030007  // MOV R3, 7
        };

        Assembly asm = new Assembly();
        asm.loadProgram(program);
        asm.execute();
    }
}
