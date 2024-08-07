package MainClasses;

import java.util.Arrays;

/**
 * This class simulates a simple assembly language processor with support for a set of instructions.
 * Instructions include arithmetic operations, memory operations, and conditional jumps.
 */
public class Assembly {
    private int[] register = new int[8]; // Array of registers (8 registers)
    private int[] memory = new int[256]; // Array of memory (256 bytes)
    private int pc = 0; // Program Counter
    private int[] program; // The program to be executed

    // Constructor to initialize the program array
    public Assembly(int programSize) {
        this.program = new int[programSize]; // Initialize program array with the specified size
    }

    public void loadProgram(String[] rawprogram) {
        int length = Math.min(program.length, rawprogram.length); // Use program length to avoid out-of-bounds
        Assembler assembler = new Assembler();
        for (int i = 0; i < length; i++) {
            int instruction = assembler.parseInstruction(rawprogram[i]);
            program[i] = instruction;
        }
    }

    /**
     * Executes the loaded program instruction by instruction.
     */
    public void execute() {
        while (pc < program.length) {
            int instruction = program[pc];

            int opcode = (instruction >> 24) & 0xFF;
            int r1 = (instruction >> 16) & 0xFF;
            int r2 = (instruction >> 8) & 0xFF;
            int addr = instruction & 0xFF;

            switch (opcode) {
                case 0x01: // MOV r1, imm
                    // Move immediate value into register
                    register[r1] = addr;
                    break;
                case 0x02: // ADD r1, r2, r3
                    // Add value from register r2 to register r3
                    register[r1] = register[r2] + register[addr];
                    break;
                case 0x03: // SUB r1, r2, r3
                    // Subtract value in register r2 from value in register r3
                    register[r1] = register[r2] - register[addr];
                    break;
                case 0x04: // MUL r1, r2, r3
                    // Multiply value in register r2 with value in register r3
                    register[r1] = register[r2] * register[addr];
                    break;
                case 0x05: // DIV r1, r2, r3
                    // Divide value in register r2 by value in register r3
                    register[r1] = register[r2] / register[addr];
                    break;
                case 0x06: // AND r1, r2, r3
                    register[r1] = register[r2] & register[addr];
                    break;
                case 0x07: // OR r1, r2, r3
                    register[r1] = register[r2] | register[addr];
                    break;
                case 0x08: // STORE r1, addr
                    // Store register value into memory
                    memory[addr] = register[r1];
                    break;
                case 0x09: // LOAD r1, addr
                    // Load value from memory into register
                    register[r1] = memory[addr];
                    break;
                case 0x0A: // JMP addr
                    // Unconditional jump to address
                    pc = addr - 1; // Subtract 1 because the pc will be incremented after this switch-case
                    break;
                case 0x0B: // JMPE r1, r2, addr
                    // Conditional jump if r1 == r2
                    if (register[r1] == register[r2]) {
                        pc = addr - 1; // Subtract 1 because the pc will be incremented after this switch-case
                    }
                    break;
                case 0x0C: // JMPN r1, r2, addr
                    // Conditional jump if r1 != r2
                    if (register[r1] != register[r2]) {
                        pc = addr - 1; // Subtract 1 because the pc will be incremented after this switch-case
                    }
                    break;
                case 0x0D: // JMPG r1, r2, addr
                    // Conditional jump if r1 > r2
                    if (register[r1] > register[r2]) {
                        pc = addr - 1; // Subtract 1 because the pc will be incremented after this switch-case
                    }
                    break;
                case 0x0E: // JMPL r1, r2, addr
                    // Conditional jump if r1 < r2
                    if (register[r1] < register[r2]) {
                        pc = addr - 1; // Subtract 1 because the pc will be incremented after this switch-case
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
