package MainClasses;

import java.util.HashMap;
import java.util.Map;

/**
 * The Assembler class simulates a simple assembler that converts assembly instructions
 * into machine code. It supports several instructions (e.g., mov, add, sub) and registers
 * (e.g., r0, r1, r2). The resulting machine code is represented as an integer.
 */
public class Assembler {

    // HashMap to store opcode mappings for each instruction mnemonic
    private static final Map<String, Integer> OPCODES = new HashMap<>();

    // HashMap to store register mappings for register names
    private static final Map<String, Integer> REGISTERS = new HashMap<>();

    // HashMap to store the number of arguments each instruction requires
    private static final Map<String, Integer> ARG_COUNTS = new HashMap<>();

    // Static block to initialize the opcode, register, and argument count maps
    static {
        // Initialize the opcode mappings for each mnemonic
        OPCODES.put("mov",  0x01);
        OPCODES.put("add",  0x02);
        OPCODES.put("sub",  0x03);
        OPCODES.put("mul",  0x04);
        OPCODES.put("div",  0x05);
        OPCODES.put("and",  0x06);
        OPCODES.put("or",   0x07);
        OPCODES.put("str",  0x08);
        OPCODES.put("ld",   0x09);
        OPCODES.put("jmp",  0x0A);
        OPCODES.put("jmpe", 0x0B);
        OPCODES.put("jmpn", 0x0C);
        OPCODES.put("jmpg", 0x0D);
        OPCODES.put("jmpl", 0x0E);
        // Add other opcodes as needed

        // Initialize register mappings
        REGISTERS.put("r0", 0x00);
        REGISTERS.put("r1", 0x01);
        REGISTERS.put("r2", 0x02);
        REGISTERS.put("r3", 0x03);
        REGISTERS.put("r4", 0x04);
        REGISTERS.put("r5", 0x05);
        REGISTERS.put("r6", 0x06);
        REGISTERS.put("r7", 0x07);
        // Add other registers as needed

        // Initialize argument counts for each instruction
        ARG_COUNTS.put("mov",  2);
        ARG_COUNTS.put("str",  2);
        ARG_COUNTS.put("ld",   2);
        ARG_COUNTS.put("jmp",  1);
        ARG_COUNTS.put("add",  3);
        ARG_COUNTS.put("sub",  3);
        ARG_COUNTS.put("mul",  3);
        ARG_COUNTS.put("div",  3);
        ARG_COUNTS.put("and",  3);
        ARG_COUNTS.put("or",   3);
        ARG_COUNTS.put("jmpe", 3);
        ARG_COUNTS.put("jmpn", 3);
        ARG_COUNTS.put("jmpg", 3);
        ARG_COUNTS.put("jmpl", 3);
        // Add argument counts for other instructions
    }

    /**
     * Converts an assembly instruction into machine code.
     *
     * @param instruction A string representing an assembly instruction.
     *                    Example: "mov r1 #5" or "add r1 r2 r3".
     * @return The corresponding machine code as an integer.
     */
    public int parseInstruction(String instruction) {
        // Split the instruction string into its individual parts
        String[] parts = instruction.split(" ");

        // Get the mnemonic (instruction operation) from the first part
        String mnemonic = parts[0];

        // Lookup the opcode for the given mnemonic
        int opcode = OPCODES.get(mnemonic);

        // Lookup the number of arguments required for the instruction
        int argCount = ARG_COUNTS.get(mnemonic);

        // Initialize the machine code with the opcode, shifting it to the top 8 bits
        int machineCode = opcode << 24;

        // Process instructions that have 2 arguments (e.g., mov, str)
        if (argCount == 2) {
            // Extract the register and immediate value from the instruction
            String reg = parts[1];
            int immediate = Integer.parseInt(parts[2].substring(1)); // Assume immediate values start with '#'
            int regCode = REGISTERS.get(reg);

            // Construct the machine code by shifting the register and immediate values into position
            machineCode |= (regCode << 16) | (immediate & 0xFFFF);

            // Process instructions that have 3 arguments (e.g., add, sub)
        } else if (argCount == 3) {
            // Extract the three registers from the instruction
            String reg1 = parts[1];
            String reg2 = parts[2];
            String reg3 = parts[3];

            // Lookup the register codes
            int regCode1 = REGISTERS.get(reg1);
            int regCode2 = REGISTERS.get(reg2);
            int regCode3 = REGISTERS.get(reg3);

            // Construct the machine code by shifting the register codes into the appropriate bits
            machineCode |= (regCode1 << 16) | (regCode2 << 8) | regCode3;
        }

        // Return the final machine code as an integer
        return machineCode;
    }
}
