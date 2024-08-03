import java.util.Arrays;

public class Assembly {
    private int[] register = new int[8];
    private int[] memory = new  int[256];

    private int pc = 0;
    private int[] program; // The program to be executed

    void loadProgram(int[] program) {
        this.program = program;
    }

    public void execute() {
        while (pc < program.length) {
            int instruction = program[pc];

            int opcode = (instruction >> 24) & 0xFF;
            int r1 = (instruction >> 16) & 0xFF;
            int r2 = (instruction >> 8) & 0xFF;
            int addr = instruction & 0xFF;

            switch (opcode) {
                case 0x01: // MOV
                    register[r1] = addr;
                    break;
                case 0x02: // ADD
                    register[r1] = register[r2]+ register[addr];
                    break;
                case 0x03: // SUB
                    register[r1] = register[r2]- register[addr];
                    break;
                case 0x04: // MUL
                    register[r1] = register[r2]* register[addr];
                    break;
                case 0x05: // DIV
                    register[r1] = register[r2]/ register[addr];
                    break;
                case 0x06: // AND
                    register[r1] = register[r2]& register[addr];
                    break;
                case 0x07: // OR
                    register[r1] = register[r2]| register[addr];
                    break;
                case 0x08: // STORE
                    memory[addr] = register[r1];
                    break;
                case 0x09: // LOAD
                    register[r1] = memory[addr];
                    break;
                case 0x0A: // JMP
                    pc = r1-1;
                    break;
                default:
                    System.out.println("Invalid instruction set!");
                    break;
            }

            System.out.println("PC: " + pc + " Registers: " + Arrays.toString(register));
            pc++;
        }
    }
}
