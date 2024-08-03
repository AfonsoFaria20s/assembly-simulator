# Simple Assembly Language Simulator

This is a basic assembly language simulator written in Java. It supports basic arithmetic, logical, and memory operations, simulating the behavior of a simple processor.

## Features

- **Arithmetic Operations**: Addition, subtraction, multiplication, and division.
- **Logical Operations**: Bitwise AND and OR.
- **Memory Operations**: Loading from and storing to memory.
- **Control Flow**: Jump instruction to alter the sequence of execution.

## Instructions

- `MOV r1, imm` (0x01): Move an immediate value to register `r1`.
- `ADD r1, r2, r3` (0x02): Add the values of registers `r2` and `r3` and store the result in `r1`.
- `SUB r1, r2, r3` (0x03): Subtract the value of register `r3` from register `r2` and store the result in `r1`.
- `MUL r1, r2, r3` (0x04): Multiply the values of registers `r2` and `r3` and store the result in `r1`.
- `DIV r1, r2, r3` (0x05): Divide the value of register `r2` by register `r3` and store the result in `r1`.
- `AND r1, r2, r3` (0x06): Perform a bitwise AND on the values of registers `r2` and `r3` and store the result in `r1`.
- `OR r1, r2, r3` (0x07): Perform a bitwise OR on the values of registers `r2` and `r3` and store the result in `r1`.
- `STORE r1, addr` (0x08): Store the value of register `r1` into memory at the specified address.
- `LOAD r1, addr` (0x09): Load the value from memory at the specified address into register `r1`.
- `JMP addr` (0x0A): Jump to the specified address in the program.

## Usage

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/assembly-simulator.git
    cd assembly-simulator
    ```

2. Compile the Java files:
    ```sh
    javac Assembly.java Main.java
    ```

3. Run the simulator with your program:
    ```sh
    java Main
    ```

## Example

```java
public class Main {
    public static void main(String[] args) {
        int[] program = {
            0x0101000A, // MOV R1, 10 (0x0A)
            0x01020005, // MOV R2, 5 (0x05)
            0x02010302, // ADD R1, R2, R3 (R1 = R2 + R3)
            0x08010000, // STORE R1 -> MEM[0x00]
            0x09010000, // LOAD R1, MEM[0x00]
            0x0A000004  // JMP to address 4 (loop)
        };

        Assembly asm = new Assembly();
        asm.loadProgram(program);
        asm.execute();
    }
}
