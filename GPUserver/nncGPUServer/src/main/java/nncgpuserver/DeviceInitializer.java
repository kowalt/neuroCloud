/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import static org.jocl.CL.*;
import org.jocl.cl_command_queue;
import org.jocl.cl_context;
import org.jocl.cl_context_properties;
import org.jocl.cl_device_id;
import org.jocl.cl_platform_id;
import org.jocl.cl_program;

/**
 *
 * @author Tomasz
 */
public class DeviceInitializer {
        
    public void initialize(int platformIndex, int deviceIndex)
    {
        final long deviceType = CL_DEVICE_TYPE_GPU;
        // Obtain the number of platforms
        int numPlatformsArray[] = new int[1];
        clGetPlatformIDs(0, null, numPlatformsArray);
        int numPlatforms = numPlatformsArray[0];

        // Obtain a platform ID
        cl_platform_id platforms[] = new cl_platform_id[numPlatforms];
        clGetPlatformIDs(platforms.length, platforms, null);
        cl_platform_id platform = platforms[platformIndex];

        // Initialize the context properties
        cl_context_properties contextProperties = new cl_context_properties();
        contextProperties.addProperty(CL_CONTEXT_PLATFORM, platform);
        
        // Obtain the number of devices for the platform
        int numDevicesArray[] = new int[1];
        clGetDeviceIDs(platform, deviceType, 0, null, numDevicesArray);
        int numDevices = numDevicesArray[0];
        
        // Obtain a device ID 
        cl_device_id devices[] = new cl_device_id[numDevices];
        clGetDeviceIDs(platform, deviceType, numDevices, devices, null);
        cl_device_id device = devices[deviceIndex];

        // Create a context for the selected device
        cl_context context = clCreateContext(
            contextProperties, 1, new cl_device_id[]{device}, 
            null, null, null);
        
        // Create a command-queue for the selected device
        cl_command_queue commandQueue = 
            clCreateCommandQueue(context, device, 0, null);
        
//        // Create the program from the source code
//        cl_program program = clCreateProgramWithSource(context,
//            1, new String[]{ programSource }, null, null);
//        
//        // Build the program
//        clBuildProgram(program, 0, null, null, null, null);
        
    }
}
