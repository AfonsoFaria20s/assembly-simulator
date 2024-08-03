import java.util.Arrays;

/**
 * A simple assembly language simulator.
 * This simulator supports basic arithmetic, logical, and memory operations.
 */
public class Assembly {
    private int[] register = new int[8]; // 8 general-purpose registers
    private int[] memory = new int[256]; // 256 bytes of memory

    private int pc = 0; // Program counter
    private int[] program; // The program to be executed

    /**
     * Loads the program into the simulator.
     * @param program An array of integers representing the program instructions.
     */
    void loadProgram(int[] program) {
        this.program = program;
    }

    /**
     * Executes the loaded program.
     */
    public void execute() {
        while (pc < program.length) {
            int instruction = program[pc];

            int opcode = (instruction >> 24) & 0xFF; // Extract opcode
            int r1 = (instruction >> 16) & 0xFF; // Extract register 1
            int r2 = (instruction >> 8) & 0xFF; // Extract register 2
            int addr = instruction & 0xFF; // Extract address/immediate value

            switch (opcode) {
                case 0x01: // MOV - Move immediate value to register
                    register[r1] = addr;
                    break;
                case 0x02: // ADD - Add values of two registers and store in register
                    register[r1] = register[r2] + register[addr];
                    break;
                case 0x03: // SUB - Subtract value of one register from another
                    register[r1] = register[r2] - register[addr];
                    break;
                case 0x04: // MUL - Multiply values of two registers
                    register[r1] = register[r2] * register[addr];
                    break;
                case 0x05: // DIV - Divide value of one register by another
                    register[r1] = register[r2] / register[addr];
                    break;
                case 0x06: // AND - Perform bitwise AND on values of two registers
                    register[r1] = register[r2] & register[addr];
                    break;
                case 0x07: // OR - Perform bitwise OR on values of two registers
                    register[r1] = register[r2] | register[addr];
                    break;
                case 0x08: // STORE - Store value of register into memory
                    memory[addr] = register[r1];
                    break;
                case 0x09: // LOAD - Load value from memory into register
                    register[r1] = memory[addr];
                    break;
                case 0x0A: // JMP - Jump to specified address in the program
                    pc = addr - 1; // -1 to compensate for the pc increment after switch
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
