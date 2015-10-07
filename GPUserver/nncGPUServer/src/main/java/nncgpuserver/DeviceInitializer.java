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
import org.jocl.cl_kernel;
import org.jocl.cl_platform_id;
import org.jocl.cl_program;

/**
 *
 * @author Tomasz
 */
public class DeviceInitializer {
    
    private cl_kernel kernel_input;
    private cl_kernel kernel_output;
    private cl_context context;
    
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
        context = clCreateContext(
            contextProperties, 1, new cl_device_id[]{device}, 
            null, null, null);
        
        // Create a command-queue for the selected device
        cl_command_queue commandQueue = 
            clCreateCommandQueue(context, device, 0, null);
        
        //Create the programs from the source code
        cl_program program_input = clCreateProgramWithSource(context,
            1, new String[]{ KernelUtil.getOutputKernelSource() }, null, null);
        
        // Build the program
        clBuildProgram(program_input, 0, null, null, null, null);
        
        cl_program program_output = clCreateProgramWithSource(context,
            1, new String[]{ KernelUtil.getOutputKernelSource() }, null, null);
        
        // Build the program
        clBuildProgram(program_output, 0, null, null, null, null);
        
        // Create the kernels
        kernel_input = clCreateKernel(program_input, "calculateNeuronInputKernel",null);
        kernel_output = clCreateKernel(program_output, "calculateNeuronOutputKernel", null);
    }

    public cl_kernel getKernel_input() {
        return kernel_input;
    }

    public void setKernel_input(cl_kernel kernel_input) {
        this.kernel_input = kernel_input;
    }

    public cl_kernel getKernel_output() {
        return kernel_output;
    }

    public void setKernel_output(cl_kernel kernel_output) {
        this.kernel_output = kernel_output;
    }

    public cl_context getContext() {
        return context;
    }

    public void setContext(cl_context context) {
        this.context = context;
    }
}
