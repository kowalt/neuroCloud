/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

/**
 *
 * @author Tomasz
 */
public class KernelUtil 
{
    private static final String INPUT_KERNEL_SOURCE= "__kernel void "+
        "calculateNeuronInputKernel(__global const float *weights, __global const float *values, __global float* sum)" +
        "{"+
        "    int gid = get_global_id(0);"+
        "    sum += values[gid]*weights[gid];"+   
        "}";
    
    private static final String OUTPUT_KERNEL_SOURCE = "__kernel float "+
        "calculateNeuronOutputKernel(__global const float af_value,__global const float *weights)" +
        "{"+
        "    int gid = get_global_id(0);"+
        "    return sum*weights[gid];" +   
        "}";
            

    public static String getOutputKernelSource() {
        return OUTPUT_KERNEL_SOURCE;
    }
    
    public static String getInputKernelSource()
    {
        return INPUT_KERNEL_SOURCE;
    }
}
