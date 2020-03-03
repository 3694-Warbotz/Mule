package frc.robot;

import java.nio.ByteBuffer;

import edu.wpi.first.hal.I2CJNI;
import edu.wpi.first.wpilibj.I2C;

public class Rangefinder {
    private static int port = OI.rangeFinderPort;
    private static byte i2c_address = OI.rangeFinderAddress; 
    private static final ByteBuffer buffer =  ByteBuffer.allocateDirect(2);

    public static void init(byte sensor_port) {
        port = sensor_port;
        I2CJNI.i2CInitialize(port);
    }

    public static int write(int data_address, int value) {
        buffer.put(0, (byte) data_address);
        buffer.put(1, (byte) value);
        
        return I2CJNI.i2CWrite(port, i2c_address, buffer, (byte) buffer.capacity());
    }

    public static byte read(int data_address, int size) {
        buffer.put(0, (byte) data_address);
		I2CJNI.i2CWrite(port, i2c_address, buffer, (byte) 1);
		I2CJNI.i2CRead(port, i2c_address, buffer, (byte) size);
		return buffer.get(0);
    }

    public static byte measure() {
        write(0x00, 0x04);
        while (read(0x01, 1) != (byte) 0100000) {
            measure();
        }
        return read(0x8f, 2);
    }

    public static int convert(byte distance) {
        int centimeters = (int) distance;
        return centimeters;
    }

    public static int start() {
        return convert(measure());
    }
}