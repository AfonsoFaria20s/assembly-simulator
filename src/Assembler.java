import java.util.HashMap;
import java.util.Map;

public class Assembler {
    private static final Map<String, Integer> OPCODES = new HashMap<>();
    private static final Map<String, Integer> REGISTERS = new HashMap<>();
    private static final Map<String, Integer> ARG_COUNTS = new HashMap<>();

    static {
        OPCODES.put("mov", 0x01);
        OPCODES.put("add", 0x02);
        OPCODES.put("sub", 0x03);
        OPCODES.put("mul", 0x04);
        OPCODES.put("div", 0x05);
        OPCODES.put("and", 0x06);
        OPCODES.put("or", 0x07);
        OPCODES.put("str", 0x08);
        OPCODES.put("ld", 0x09);
        OPCODES.put("jmp", 0x0A);
        OPCODES.put("jmpe", 0x0B);
        OPCODES.put("jmpn", 0x0C);
        OPCODES.put("jmpg", 0x0D);
        OPCODES.put("jmpl", 0x0E);
        // Add other opcodes as needed

        REGISTERS.put("eax", 0x00);
        REGISTERS.put("ebx", 0x01);
        REGISTERS.put("ecx", 0x02);
        REGISTERS.put("edx", 0x03);
        REGISTERS.put("eex", 0x04);
        REGISTERS.put("efx", 0x05);
        REGISTERS.put("egx", 0x06);
        REGISTERS.put("ehx", 0x07);
        // Add other registers as needed

        ARG_COUNTS.put("mov",  2);
        ARG_COUNTS.put("str",  2);
        ARG_COUNTS.put("ld",   2);
        ARG_COUNTS.put("jmp",  2);
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

    public int parseInstruction(String instruction) {
        String[] parts = instruction.split(" ");
        String mnemonic = parts[0];
        int opcode = OPCODES.get(mnemonic);
        int argCount = ARG_COUNTS.get(mnemonic);

        int machineCode = opcode << 24;

        if (argCount == 2) {
            String reg = parts[1];
            int immediate = Integer.parseInt(parts[2].substring(1)); // Assuming immediate values are prefixed with '#'
            int regCode = REGISTERS.get(reg);

            machineCode |= (regCode << 16) | (immediate & 0xFFFF);
        } else if (argCount == 3) {
            String reg1 = parts[1];
            String reg2 = parts[2];
            String reg3 = parts[3];

            int regCode1 = REGISTERS.get(reg1);
            int regCode2 = REGISTERS.get(reg2);
            int regCode3 = REGISTERS.get(reg3);

            machineCode |= (regCode1 << 16) | (regCode2 << 8) | regCode3;
        }

        return machineCode;
    }
}
