# Simple Assembly Language Simulator

This is a basic assembly language simulator written in Java. It supports basic arithmetic, logical, and memory operations, simulating the behavior of a simple processor.

## Features

- **Arithmetic Operations**: Addition, subtraction, multiplication, and division.
- **Logical Operations**: Bitwise AND and OR.
- **Memory Operations**: Loading from and storing to memory.
- **Control Flow**: Jump instructions to alter the sequence of execution.
- **Conditional Jumps**: Conditional jumps based on equality, inequality, and relational operators.

## Instructions

- `MOV r1, imm` (0x01): Move an immediate value to register `r1`.
- `ADD r1, r2, addr` (0x02): Add the value of register `r2` to the value from memory at address `addr` and store the result in `r1`.
- `SUB r1, r2, addr` (0x03): Subtract the value from memory at address `addr` from the value of register `r2` and store the result in `r1`.
- `MUL r1, r2, addr` (0x04): Multiply the value of register `r2` by the value from memory at address `addr` and store the result in `r1`.
- `DIV r1, r2, addr` (0x05): Divide the value of register `r2` by the value from memory at address `addr` and store the result in `r1`.
- `AND r1, r2, addr` (0x06): Perform a bitwise AND on the values of registers `r2` and `addr` and store the result in `r1`.
- `OR r1, r2, addr` (0x07): Perform a bitwise OR on the values of registers `r2` and `addr` and store the result in `r1`.
- `STORE r1, addr` (0x08): Store the value of register `r1` into memory at the specified address `addr`.
- `LOAD r1, addr` (0x09): Load the value from memory at the specified address `addr` into register `r1`.
- `JMP addr` (0x0A): Jump to the specified address `addr` in the program.
- `JMPE r1, r2, addr` (0x0B): Conditional jump to address `addr` if the value of register `r1` equals the value of register `r2`.
- `JMPN r1, r2, addr` (0x0C): Conditional jump to address `addr` if the value of register `r1` does not equal the value of register `r2`.
- `JMPG r1, r2, addr` (0x0D): Conditional jump to address `addr` if the value of register `r1` is greater than the value of register `r2`.
- `JMPL r1, r2, addr` (0x0E): Conditional jump to address `addr` if the value of register `r1` is less than the value of register `r2`.
- `NOOP` (0xFF): No operation.

## Usage

1. Clone the repository:
    ```sh
    git clone https://github.com/AfonsoFaria20s/assembly-simulator.git
    cd assembly-simulator
    ```

2. Compile the Java files:
    ```sh
    javac Assembly.java Main.java
    ```

3. Create a file named `program.txt` with your instructions, each on a new line. For example:
    ```
    MOV R1, 10
    MOV R2, 5
    ADD R1, R2, 0
    STORE R1, 0
    LOAD R1, 0
    JMP 0
    ```

4. Run the simulator with your program:
    ```sh
    java Main
    ```

## Example

The following Java code demonstrates how to set up and run a program with the simulator:

```java
public class Main {
   public static void main(String[] args) {
      // Path to the program.txt file
      String filePath = "program.txt"; // Update this path if necessary

      try {
         // Read all lines from the file into a List<String>
         List<String> lines = Files.readAllLines(Paths.get(filePath));

         // Convert List<String> to String[]
         String[] program = lines.toArray(new String[0]);

         // Create an Assembly instance with appropriate size
         Assembly asm = new Assembly(program.length);

         // Load the program instructions into the Assembly instance
         asm.loadProgram(program);

         // Execute the loaded program
         asm.execute();

      } catch (IOException e) {
         // Handle potential IOExceptions such as file not found
         System.err.println("Error reading the file: " + e.getMessage());
      }
   }
}
```
# Notes
- Ensure that program.txt is located in the same directory as Main.java or adjust the file path accordingly.
- The simulator uses a busy-wait loop to simulate delays; you can adjust or remove this as needed.
- Feel free to modify and extend the simulator according to your needs. Happy coding!