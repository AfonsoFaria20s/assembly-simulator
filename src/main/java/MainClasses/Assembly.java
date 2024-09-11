package MainClasses;

import GUI.windowComponents.FlagsPanel;
import GUI.windowComponents.MemoryPanel;
import GUI.windowComponents.RegistersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Assembly {
    private int[] register = new int[8]; // Array of registers (8 registers)
    private int[] memory = new int[256]; // Array of memory (256 bytes)

    private int pc = 0; // Program Counter
    private int[] program; // The program to be executed
    private RegistersPanel registersPanel;
    private MemoryPanel memoryPanel;
    private Timer timer;
    private FlagsPanel flagsPanel;

    // Constructor to initialize the program array, registers panel, and memory panel
    public Assembly(int programSize, RegistersPanel registersPanel, MemoryPanel memoryPanel, FlagsPanel flagsPanel) {
        this.program = new int[programSize]; // Initialize program array with the specified size
        this.registersPanel = registersPanel; // Initialize registers panel
        this.memoryPanel = memoryPanel; // Initialize memory panel
        this.flagsPanel = flagsPanel;
    }

    public void loadProgram(String[] rawprogram) {
        int length = Math.min(program.length, rawprogram.length); // Use program length to avoid out-of-bounds
        Assembler assembler = new Assembler();
        for (int i = 0; i < length; i++) {
            int instruction = assembler.parseInstruction(rawprogram[i]);
            program[i] = instruction;
        }
    }

    public void execute() {
        flagsPanel.resetFlags();
        final int delay = 500; // 500 milliseconds delay between iterations
        final int maxIterations = 1000; // Set a reasonable limit for debugging

        ActionListener taskPerIteration = new ActionListener() {
            private int iteration = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (pc < program.length && iteration < maxIterations) {
                    int instruction = program[pc];

                    int opcode = (instruction >> 24) & 0xFF;
                    int r1 = (instruction >> 16) & 0xFF;
                    int r2 = (instruction >> 8) & 0xFF;
                    int addr = instruction & 0xFF;

                    switch (opcode) {
                        case 0x01: // MOV r1, imm
                            register[r1] = addr;
                            break;
                        case 0x02: // ADD r1, r2, r3
                            register[r1] = register[r2] + register[addr];
                            flagsPanel.updateFlags(register[r1]);
                            break;
                        case 0x03: // SUB r1, r2, r3
                            register[r1] = register[r2] - register[addr];
                            flagsPanel.updateFlags(register[r1]);
                            break;
                        case 0x04: // MUL r1, r2, r3
                            register[r1] = register[r2] * register[addr];
                            flagsPanel.updateFlags(register[r1]);
                            break;
                        case 0x05: // DIV r1, r2, r3
                            register[r1] = register[r2] / register[addr];
                            flagsPanel.updateFlags(register[r1]);
                            break;
                        case 0x06: // AND r1, r2, r3
                            register[r1] = register[r2] & register[addr];
                            flagsPanel.updateFlags(register[r1]);
                            break;
                        case 0x07: // OR r1, r2, r3
                            register[r1] = register[r2] | register[addr];
                            flagsPanel.updateFlags(register[r1]);
                            break;
                        case 0x08: // STORE r1, addr
                            memory[addr] = register[r1];
                            flagsPanel.updateFlags(register[r1]);
                            break;
                        case 0x09: // LOAD r1, addr
                            register[r1] = memory[addr];
                            break;
                        case 0x0A: // JMP addr
                            pc = addr - 1;
                            break;
                        case 0x0B: // JMPE r1, r2, addr
                            if (register[r1] == register[r2]) {
                                pc = addr - 1;
                            }
                            break;
                        case 0x0C: // JMPN r1, r2, addr
                            if (register[r1] != register[r2]) {
                                pc = addr - 1;
                            }
                            break;
                        case 0x0D: // JMPG r1, r2, addr
                            if (register[r1] > register[r2]) {
                                pc = addr - 1;
                            }
                            break;
                        case 0x0E: // JMPL r1, r2, addr
                            if (register[r1] < register[r2]) {
                                pc = addr - 1;
                            }
                            break;
                        case 0xFF: // NO OP
                            break;
                        default:
                            System.out.println("Invalid instruction set!");
                            break;
                    }

                    // Update the UI with new register and memory values
                    SwingUtilities.invokeLater(() -> {
                        registersPanel.getRegistersTable().updateRegisterValues(register);
                        memoryPanel.updateMemoryValues(memory);
                    });

                    pc++;
                    iteration++;
                } else {
                    // Stop the timer when done
                    timer.stop();
                    flagsPanel.resetFlags();
                    if (iteration >= maxIterations) {
                        System.out.println("Execution halted: Maximum iterations reached.");
                    }
                }
            }
        };

        // Create a timer that calls the taskPerIteration every 'delay' milliseconds
        timer = new Timer(delay, taskPerIteration);
        timer.start();
    }

    public void reset() {
        // Reset memory and registers to 0
        Arrays.fill(memory, 0);
        Arrays.fill(register, 0);

        // Reset PC if needed
        pc = 0;

        // Optionally update the UI
        SwingUtilities.invokeLater(() -> {
            registersPanel.getRegistersTable().updateRegisterValues(register);
            memoryPanel.updateMemoryValues(memory);
        });
    }

    public Timer getTimer() {
        return this.timer;
    }

    public int[] getRegister() {
        return register;
    }

    public void setRegister(int[] register) {
        this.register = register;
    }

    public int[] getMemory() {
        return memory;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }
}
