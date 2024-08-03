import java.util.Arrays;

/**
 * This class simulates a simple assembly language processor with support for a set of instructions.
 * Instructions include arithmetic operations, memory operations, and conditional jumps.
 */
public class Assembly {
    private int[] register = new int[8]; // Array of registers (8 registers)
    private int[] memory = new int[256]; // Array of memory (256 bytes)

    private int pc = 1; // Program Counter
    private int[] program; // The program to be executed

    /**
     * Loads the program into the processor.
     * @param program An array of integers representing the machine code instructions.
     */
    void loadProgram(int[] program) {
        this.program = program;
    }

    /**
     * Executes the loaded program instruction by instruction.
     */
    public void execute() {
        while (pc < program.length) {
            int instruction = program[pc];

            int opcode = (instruction >> 24) & 0xFF;
            int r1 = (instruction >> 16) & 0xFF;
            int r2OrImm = (instruction >> 8) & 0xFF;
            int addrOrUnused = instruction & 0xFF;

            switch (opcode) {
                case 0x01: // MOV r1, imm
                    // Move immediate value into register
                    register[r1] = addrOrUnused;
                    break;
                case 0x02: // ADD r1, r2, imm
                    // Add immediate value to register value
                    register[r1] = register[r2OrImm] + addrOrUnused;
                    break;
                case 0x03: // ADD r1, r2, addr
                    // Add value from memory to register value
                    register[r1] = register[r2OrImm] + memory[addrOrUnused];
                    break;
                case 0x04: // SUB r1, r2, imm
                    // Subtract immediate value from register value
                    register[r1] = register[r2OrImm] - addrOrUnused;
                    break;
                case 0x05: // SUB r1, r2, addr
                    // Subtract value from memory from register value
                    register[r1] = register[r2OrImm] - memory[addrOrUnused];
                    break;
                case 0x06: // MUL r1, r2, imm
                    // Multiply register value by immediate value
                    register[r1] = register[r2OrImm] * addrOrUnused;
                    break;
                case 0x07: // MUL r1, r2, addr
                    // Multiply register value by value from memory
                    register[r1] = register[r2OrImm] * memory[addrOrUnused];
                    break;
                case 0x08: // DIV r1, r2, imm
                    // Divide register value by immediate value
                    register[r1] = register[r2OrImm] / addrOrUnused;
                    break;
                case 0x09: // DIV r1, r2, addr
                    // Divide register value by value from memory
                    register[r1] = register[r2OrImm] / memory[addrOrUnused];
                    break;
                case 0x0A: // LOAD r1, addr
                    // Load value from memory into register
                    register[r1] = memory[addrOrUnused];
                    break;
                case 0x0B: // STORE r1, addr
                    // Store register value into memory
                    memory[addrOrUnused] = register[r1];
                    break;
                case 0x0C: // JMP addr
                    // Unconditional jump to address
                    pc = addrOrUnused;
                    break;
                case 0x0D: // JMP_EQ r1, r2, addr
                    // Conditional jump if r1 == r2
                    if (register[r1] == register[r2OrImm]) {
                        pc = addrOrUnused;
                    }
                    break;
                case 0x0E: // JMP_NE r1, r2, addr
                    // Conditional jump if r1 != r2
                    if (register[r1] != register[r2OrImm]) {
                        pc = addrOrUnused;
                    }
                    break;
                case 0x0F: // JMP_GT r1, r2, addr
                    // Conditional jump if r1 > r2
                    if (register[r1] > register[r2OrImm]) {
                        pc = addrOrUnused;
                    }
                    break;
                case 0x10: // JMP_LT r1, r2, addr
                    // Conditional jump if r1 < r2
                    if (register[r1] < register[r2OrImm]) {
                        pc = addrOrUnused;
                    }
                    break;
                case 0xFF: // NO OP
                    break;
                default:
                    System.out.println("Invalid instruction set!");
                    break;
            }

            System.out.println("PC: " + pc + " Registers: " + Arrays.toString(register) + " Memory: " + Arrays.toString(memory));
            pc++;

            // Simulate delay with busy-wait loop
            long endTime = System.currentTimeMillis() + 500; // 500 milliseconds delay
            while (System.currentTimeMillis() < endTime) {
                // Busy-wait loop
            }
        }
    }
}
